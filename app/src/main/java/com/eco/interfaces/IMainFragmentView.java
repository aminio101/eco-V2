package com.eco.interfaces;

import com.eco.entitys.RubbishEntity;

import java.util.ArrayList;

public interface IMainFragmentView  {
    void showList(ArrayList<RubbishEntity> result);

    void rGetList();

    void loadMapFragment();

    void showUserScore(String score);

    void rGetUser();

    void rSendFCM();
}
