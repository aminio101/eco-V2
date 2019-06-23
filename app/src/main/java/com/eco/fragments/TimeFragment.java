package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.eco.R;
import com.eco.interfaces.ITimeFragmentView;
import com.eco.presenters.TimeFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeFragment extends Fragment implements ITimeFragmentView {
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    RelativeLayout root;
    TimeFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_fragment, container, false);
        init();
        presenter.getTimes();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        presenter = new TimeFragmentPresenter(this, getContext(), progressBar, root);
    }

}
