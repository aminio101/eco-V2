package com.eco.interfaces;

import com.eco.entitys.DriverEntity;
import com.eco.entitys.ItemEntity;
import com.eco.entitys.RequestEntity;

import java.util.ArrayList;

public interface IFragmentRateDriverView {
    void showList(DriverEntity result, ArrayList<ItemEntity> items );

    void rGetList(int id);

    void rSendComment(float rating, int driverId);

    void success();
}
