package com.eco.interfaces;

import com.eco.entitys.DriverEntity;
import com.eco.entitys.RequestEntity;

public interface IFragmentRateDriverView {
    void showList(DriverEntity result);

    void rGetList(int id);
}
