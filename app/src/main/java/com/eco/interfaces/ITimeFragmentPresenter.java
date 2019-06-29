package com.eco.interfaces;

import com.eco.entitys.LocationEntity;
import com.eco.entitys.RunDatePeriodsEntity;

public interface ITimeFragmentPresenter extends IBasePresenter {
    void getTimes();
    void getRequestNumber(LocationEntity locationEntity);
    void getNow();
    void getAdvertising();

    void nextFragment(RunDatePeriodsEntity runDatePeriods);
}
