package com.eco.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eco.R;
import com.eco.adapter.MainListAdapter;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.IMainFragmentPresenter;
import com.eco.interfaces.IMainFragmentView;
import com.eco.interfaces.MainAdapterListener;
import com.eco.presenters.MainFragmentPresenter;
import com.eco.viewHolder.MainListViewHolder;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements IMainFragmentView {
    View view;
    IMainFragmentPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.list)
    RecyclerView recyclerView;
    MainListAdapter mainListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this,view);
        init();
        presenter.getList();
        return view;
    }
    MainAdapterListener mainAdapterListener = new MainAdapterListener() {
        @Override
        public void onClick(int i ) {
            MainListViewHolder mainListViewHolder = (MainListViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            mainListViewHolder.setSelected();
        }
    };
    private void init() {
        presenter = new MainFragmentPresenter(this,getContext(),progressBar,root);
        mainListAdapter = new MainListAdapter(getContext(),mainAdapterListener);
        recyclerView.setAdapter(mainListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void showList(ArrayList<RubbishEntity> result) {
        mainListAdapter.addItem(result);
    }

    @Override
    public void rGetList() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getList();
            }
        });
    }
}
