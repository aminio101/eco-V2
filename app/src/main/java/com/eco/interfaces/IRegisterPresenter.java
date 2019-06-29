package com.eco.interfaces;

import com.eco.entitys.UserEntity;

public interface IRegisterPresenter {
    void checkedChange(boolean isChecked);

    void register(UserEntity user);
}
