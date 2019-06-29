package com.eco.interfaces;

import com.eco.entitys.RequstUserUpdateEntity;
import com.eco.entitys.UserEntity;

public interface IEditProfileView {

    void success(UserEntity result
    );
    void rupdate(RequstUserUpdateEntity requstUserUpdateEntity);

    void callHome();

    void checkedOk();
}
