package com.eco.interfaces;

import com.eco.entitys.DayEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.UserNumberEntity;

import java.util.ArrayList;

public interface ITimeFragmentView {
    void showRequestNumber(ArrayList<UserNumberEntity> result);

    void rGetRequestNumber(LocationEntity locationEntity);
    void showVideo(String url);
    void showGif(String s);
    void showGif(int s);

    void rGetAdvertising();

    void rGetNow();

    void rGetTimes();

    void showTimes(ArrayList <DayEntity> list);
}
