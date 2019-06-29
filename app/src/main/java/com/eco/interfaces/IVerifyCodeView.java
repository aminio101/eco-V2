package com.eco.interfaces;

public interface IVerifyCodeView {
    void rVerifyCode();

    void goToRegisterActivity(String mobile);

    void rGetUser(String userName, String token);

    void goToMainActivity();

    void rSendCode();

    void onSuccessSendCode();
}
