package com.eco.interfaces;

import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;

public interface IXChangeView {
    void rGetList(int id);

    void showList(ListeEntity<XChangeEntity> result);
}
