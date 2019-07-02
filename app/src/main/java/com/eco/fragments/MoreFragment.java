package com.eco.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.eco.PrefManager;
import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.activityes.SplashActivity;
import com.eco.interfaces.IMoreFragmentPresenter;
import com.eco.interfaces.IMoreFragmentView;
import com.eco.presenters.MoreFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreFragment extends Fragment implements IMoreFragmentView {
    View view;
    IMoreFragmentPresenter presenter;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.roott)
    ConstraintLayout root;

    @OnClick(R.id.exit)
    public void exit() {
        presenter.exit();
    }

    @OnClick(R.id.profile)
    public void loadPofileFragment() {
        ((MainActivity) getActivity()).loadEditProfileFragment();
    }

    @OnClick(R.id.wallet)
    public void loadWalletFragment() {
        ((MainActivity) getActivity()).loadFragmentWallet();


    }

    @OnClick(R.id.invite)
    public void loadInviteFragment() {
        ((MainActivity) getActivity()).loadIntroduceFragment();

    }

    @OnClick(R.id.about_us)
    public void loadAboutUs() {
        Toast.makeText(getContext(), "ناقص", Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new MoreFragmentPresenter(this, getContext(), progressBar, root);
    }

    @Override
    public void successExit() {
        Intent intent = new Intent(getActivity(), SplashActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void rExit() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.exit();
            }
        });
    }
}
