package com.eco.interfaces;

import com.eco.entitys.RubbishEntity;

import java.text.ParseException;

public interface IFinalFragmentPresenter extends IBasePresenter {

    void delete(RubbishEntity tag);

    void getList();

    void sendRequest();

    void getTime() throws ParseException;
}
