package com.eco.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.eco.PV;
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
    public void loadProfileFragment() {
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
        ((MainActivity) getActivity()).AboutUs();
    }


    @OnClick(R.id.question)
    public void qu() {
        if (PV.versionEntity.challenge_number>0){
            String urlString = PV.versionEntity.challenge_url_page;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                getActivity().startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null);
                getActivity().startActivity(intent);
            }
        }else
            Toast.makeText(getActivity(), "شما چالشی ندارید", Toast.LENGTH_LONG).show();

    }

    @OnClick(R.id.shop)
    public void shop() {
        Toast.makeText(getActivity(), "این بخش غیر فعال میباشد", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.learn)
    public void learn() {
        Toast.makeText(getActivity(), "این بخش غیر فعال میباشد", Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_fragment, container, false);
        hideKeyboard();
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        ((MainActivity) getActivity()).setTollbarName(" بیشتر");
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

    void hideKeyboard() {
        View view1 = getActivity().getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
