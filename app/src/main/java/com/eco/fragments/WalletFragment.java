package com.eco.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.eco.R;
import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.presenters.WalletFragmentPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletFragment extends Fragment implements IWalletFragmentView {
    IWalletPresenter presenter;
    View view;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ScrollView root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wallet_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new WalletFragmentPresenter(this, getContext(), progressBar, root);
    }

    @Override
    public void success() {

    }

    @Override
    public void rPay(int score) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.pay(score);
            }
        });
    }
}
