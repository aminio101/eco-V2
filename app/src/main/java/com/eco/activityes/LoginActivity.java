package com.eco.activityes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.R;
import com.eco.interfaces.ILoginView;
import com.eco.presenters.LoginPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.login_button_send_code)
    public void sendCode() {
        presenter.sendCode(editTextPhoneNumber.getText().toString());
        onBackClick();
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view1 = getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.editText_login_number)
    public void onClick() {
        onApplyClick();
    }

    @BindView(R.id.login_progress_circular_send_code)
    ProgressBar progressBar;
    @BindView(R.id.guideline7)
    Guideline guidelineTop;
    @BindView(R.id.editText_login_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.login_button_send_code)
    Button buttonSendCode;
    @BindView(R.id.root)
    ConstraintLayout root;
    LoginPresenter presenter;
    ConstraintSet constraintSetOld;
    ConstraintSet constraintSetNew;
    private boolean openKeyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
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


    private void init() {
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this, this, progressBar, buttonSendCode);
        constraintSetNew = new ConstraintSet();
        constraintSetOld = new ConstraintSet();
        constraintSetOld.clone(root);
        constraintSetNew.clone(this, R.layout.activity_login_2);
        openKeyboard = false;
        presenter.checkLogin();

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void rSendCode() {
        DialogConnection dialogConnection = new DialogConnection(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendCode(editTextPhoneNumber.getText().toString());
            }
        });
    }

    @Override
    public void goToVeryFyCodeActivity() {
        Intent intent = new Intent(LoginActivity.this, VerifyCodeActivity.class);
        intent.putExtra("phone", editTextPhoneNumber.getText().toString());
        startActivity(intent);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
