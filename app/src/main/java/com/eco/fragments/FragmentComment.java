package com.eco.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eco.PV;
import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.entitys.DriverEntity;
import com.eco.entitys.ItemEntity;
import com.eco.interfaces.IFragmentRateDriverPresenter;
import com.eco.interfaces.IFragmentRateDriverView;
import com.eco.presenters.FragmentRateDriverPresenter;
import com.eco.views.DialogConnection;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentComment extends Fragment implements IFragmentRateDriverView {
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
    DriverEntity driverEntity;
    @BindView(R.id.rat)
    RatingBar ratingBar;

    @OnClick(R.id.button_submit)
    public void onClick() {
        assert driverEntity != null;
        presenter.sendComment(ratingBar.getRating(), driverEntity.driverId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rate_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        presenter.getList(id);
        return view;
    }

    private void init() {
        assert getArguments() != null;
        id = Integer.valueOf(getArguments().getString("number"));
        presenter = new FragmentRateDriverPresenter(this, getContext(), progress, root);
    }

    @Override
    public void showList(DriverEntity result, ArrayList<ItemEntity> items) {
        name.setText(result.name + " " + result.family);

        this.driverEntity = result;
        assert result.thumbpic != null;
        Glide.with(getContext()).load(PV.getImage(result.thumbpic)).into(profile);

    }

    @Override
    public void rGetList(int id) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getList(id);
            }
        });
    }

    @Override
    public void rSendComment(float rating, int driverId) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendComment(rating, driverId);
            }
        });
    }

    @Override
    public void success() {
        ((MainActivity) getActivity()).loadMainFragment();

    }
}
