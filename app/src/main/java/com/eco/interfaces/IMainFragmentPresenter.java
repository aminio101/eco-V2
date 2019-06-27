package com.eco.interfaces;

import com.eco.viewHolder.MainListViewHolder;

import java.util.ArrayList;

public interface IMainFragmentPresenter extends IBasePresenter {
    void getList();

    void save(ArrayList<MainListViewHolder> viewHolders);
}
