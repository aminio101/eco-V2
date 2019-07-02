package com.eco.interfaces;

public interface IFragmentRateDriverPresenter extends IBasePresenter{
    void getList(int id);

    void sendComment(float rating,int id);
}
