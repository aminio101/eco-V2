package com.eco.interfaces;

import com.eco.entitys.RubbishEntity;

public interface IFinalFragmentPresenter extends IBasePresenter {

    void delete(RubbishEntity tag);

    void getList();

    void sendRequest();

}
