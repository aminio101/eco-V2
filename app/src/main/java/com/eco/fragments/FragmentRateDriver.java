package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eco.PV;
import com.eco.R;
import com.eco.entitys.DriverEntity;
import com.eco.entitys.RequestEntity;
import com.eco.interfaces.IFragmentRateDriverPresenter;
import com.eco.interfaces.IFragmentRateDriverView;
import com.eco.presenters.FragmentRateDriverPresenter;
import com.eco.views.DialogConnection;

import java.sql.Driver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentRateDriver  extends Fragment implements IFragmentRateDriverView {
    View view;
    IFragmentRateDriverPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.root)
    ScrollView root;
    @BindView(R.id.image)
    ImageView profile;
    @BindView(R.id.textView_name)
    TextView name;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenr_rate_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        presenter.getList(id);
        return view;
    }

    private void init() {
        assert getArguments() != null;
        id = getArguments().getInt("id");
        presenter = new FragmentRateDriverPresenter(this,getContext(),progress,root);
    }

    @Override
    public void showList(DriverEntity result) {
        name.setText(result.name + " "+result.family);

        if (result.thumbpic != null)
            Glide.with(getContext()).load(PV.getImage( result.thumbpic)).into(profile);

    }

    @Override
    public void rGetList(int id) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getList(id);            }
        });
    }
}
