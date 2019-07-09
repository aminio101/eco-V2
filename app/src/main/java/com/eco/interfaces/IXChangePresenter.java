package com.eco.interfaces;

import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.viewHolder.XChangeViewHolder;

public interface IXChangePresenter extends IBasePresenter {
    void getList(int id);
    void getMinaList(ListeEntity<XChangeEntity> list,int id);

    void delete(XChangeEntity xChangeEntity, XChangeViewHolder xChangeViewHolder);

}
