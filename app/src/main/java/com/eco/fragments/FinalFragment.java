package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.R;
import com.eco.adapter.FinalFragmentAdapter;
import com.eco.entitys.RubbishEntity;
import com.eco.interfaces.IFinalFragmentPresenter;
import com.eco.interfaces.IFinalFragmentView;
import com.eco.presenters.FinalFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinalFragment extends Fragment implements IFinalFragmentView {
    IFinalFragmentPresenter presenter;
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    NestedScrollView root;
    @BindView(R.id.list)
    RecyclerView list;
    FinalFragmentAdapter adapter;
    @OnClick(R.id.button) public void sendRequst(){
        presenter.sendRequest();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.final_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        presenter.getList();
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.delete((RubbishEntity) v.getTag());
        }
    };

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void init() {
        list.setNestedScrollingEnabled(false);
        adapter = new FinalFragmentAdapter(getContext(), onClickListener);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        presenter = new FinalFragmentPresenter(this, getContext(), progressBar, root);
        Log.i("data", PV.requestEntity.toString());
    }

    @Override
    public void showList(ArrayList<RubbishEntity> list) {
        adapter.add(list);
    }

    @Override
    public void rSendRequest() {
        DialogConnection  dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendRequest();
            }
        });
    }

    @Override
    public void success() {
        adapter.hideDelete();
    }
}
