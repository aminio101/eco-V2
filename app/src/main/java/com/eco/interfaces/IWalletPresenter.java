package com.eco.interfaces;

public interface IWalletPresenter extends IBasePresenter {
    void pay(int score);

    void checkZeroGrade();

    void checkValue(String grade, String shaba);
}
