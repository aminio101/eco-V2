package com.eco.interfaces;

import com.eco.entitys.VerifiCodeEntity;

public interface IVerifyCodePresenter {
    void verifyCode(VerifiCodeEntity verifiCodeentity);
    void sendCode(String phone);
    void getUserData(String userName, String token);
}
