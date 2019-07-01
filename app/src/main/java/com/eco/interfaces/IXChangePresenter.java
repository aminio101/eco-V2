package com.eco.interfaces;

import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;

public interface IXChangePresenter extends IBasePresenter {
    void getList(int id);
    void getMinaList(ListeEntity<XChangeEntity> list,int id);
}
