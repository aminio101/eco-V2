package com.eco.interfaces;

import com.eco.entitys.ListeEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.viewHolder.XChangeViewHolder;

public interface IXChangeView {
    void rGetList(int id);

    void showList(ListeEntity<XChangeEntity> result);

    void rDelete(XChangeEntity xChangeEntity, XChangeViewHolder xChangeEntity1);

    void delete(XChangeEntity xChangeEntity);

    void showNull();
}
