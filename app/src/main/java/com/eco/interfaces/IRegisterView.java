package com.eco.interfaces;

import com.eco.entitys.SignupAnswerEntity;
import com.eco.entitys.UserEntity;

public interface IRegisterView {
    void isChecked();

    void notChecked();

    void success(SignupAnswerEntity result);

    void rRefister(UserEntity user);
}
