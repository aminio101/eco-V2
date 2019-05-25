package com.eco.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.R;
import com.eco.entitys.VerifiCodeEntity;
import com.eco.interfaces.IVerifyCodeView;
import com.eco.presenters.VerifyCodePresenter;
import com.eco.views.DialogConnection;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyCodeActivity extends Activity implements IVerifyCodeView {
    @BindView(R.id.verify_cod_button_check_code)
    Button btnVerifyCode;
    @BindView(R.id.verify_code_text)
    OtpView otpViewCode;
    @BindView(R.id.verify_code_progress_circular_send_code)
    ProgressBar progressBar;
    @BindView(R.id.verify_code_text_timer)
    TextView textViewTimer;
    VerifyCodePresenter presenter;
    CountDownTimer countDownTimer;
    @BindView(R.id.root)
    ConstraintLayout root;
    ConstraintSet constraintSetOld;
    ConstraintSet constraintSetNew;
    private boolean openKeyboard;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.verify_cod_button_check_code)
    public void verifyCode() {
        hideKeyboard();
        onBackClick();
        presenter.verifyCode(new VerifiCodeEntity(getIntent().getStringExtra("phone"),
                otpViewCode.getText().toString()));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.verify_code_text)
    public void setOpenKeyboard(){
        onApplyClick();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onApplyClick() {
        Transition transition = new ChangeBounds();
        transition.setInterpolator(new OvershootInterpolator());
        transition.setDuration(2000);
        TransitionManager.beginDelayedTransition(root, transition);

        if (!openKeyboard) {
            constraintSetNew.applyTo(root);
            openKeyboard = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onBackClick() {
        Transition transition = new ChangeBounds();
        transition.setInterpolator(new OvershootInterpolator());
        transition.setDuration(2000);
        TransitionManager.beginDelayedTransition(root, transition);

        if (openKeyboard) {
            constraintSetOld.applyTo(root);
            openKeyboard = false;
        }
    }
    @OnClick(R.id.verify_code_text_timer)
    public void sendCode() {
        if (textViewTimer.getText().equals("ارسال مجدد")) {
            presenter.sendCode(getIntent().getStringExtra("phone"));
        }
    }

    private void hideKeyboard() {
        View view1 = getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_cod);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        presenter = new VerifyCodePresenter(this, this, progressBar, btnVerifyCode);
        otpViewCode.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                presenter.verifyCode(new VerifiCodeEntity(getIntent().getStringExtra("phone"), otp));
            }
        });
        countDownTimer = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setTextColor(Color.BLACK);
                textViewTimer.setText("ارسال کد احراز هویت  " + millisUntilFinished / 1000 + "  ثانیه ای دیگر ");
            }

            public void onFinish() {
                textViewTimer.setTextColor(Color.BLUE);
                textViewTimer.setText("ارسال مجدد");
            }
        };
        countDownTimer.start();

        constraintSetNew = new ConstraintSet();
        constraintSetOld = new ConstraintSet();
        constraintSetOld.clone(root);
        constraintSetNew.clone(this, R.layout.activity_verify_cod_2);
        openKeyboard = false;

    }

    @Override
    public void rVerifyCode() {
        DialogConnection dialogConnection = new DialogConnection(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.verifyCode(new VerifiCodeEntity(getIntent().getStringExtra("phone"), otpViewCode.getText().toString()));
            }
        });
    }

    @Override
    public void goToRegisterActivity() {
        startActivity(new Intent(VerifyCodeActivity.this, RegisterActivity.class));
    }

    @Override
    public void rGetUser(final String userName, final String token) {
        DialogConnection dialogConnection = new DialogConnection(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUserData(userName, token);
            }
        });
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(VerifyCodeActivity.this, MainActivity.class));
    }

    @Override
    public void rSendCode() {
        DialogConnection dialogConnection = new DialogConnection(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendCode(getIntent().getStringExtra("phone"));
            }
        });
    }

    @Override
    public void onSuccessSendCode() {
        countDownTimer.start();
    }
}
