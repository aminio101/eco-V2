package com.eco.interfaces;

import com.eco.entitys.RubbishEntity;

import java.util.ArrayList;

public interface IFinalFragmentView {

    void showList(ArrayList<RubbishEntity> list);

    void rSendRequest();

    void success();
}
