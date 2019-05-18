package com.eco.activityes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eco.R;
import com.eco.interfaces.ILoginView;
import com.eco.presenters.LoginPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    @OnClick(R.id.login_button_send_code) public void sendCode(){
        presenter.sendCode(editTextPhoneNumber.getText().toString());
    }
    @BindView(R.id.login_progress_circular_send_code)
    ProgressBar progressBar ;
    @BindView(R.id.editText_login_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.login_button_send_code)
    Button buttonSendCode;
    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this,this,progressBar,buttonSendCode);
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
        Intent intent =new Intent(LoginActivity.this, VerifyCodeActivity.class);
        intent.putExtra("phone", editTextPhoneNumber.getText().toString());
        startActivity(intent);
    }

}
