package com.eco.interfaces;

import com.eco.entitys.LocationEntity;

public interface ITimeFragmentPresenter {
    void getTimes();
    void getRequestNumber(LocationEntity locationEntity);
    void getNow();
    void getAdvertising();
}
