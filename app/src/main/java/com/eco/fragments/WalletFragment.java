package com.eco.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eco.PrefManager;
import com.eco.R;
import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.presenters.WalletFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletFragment extends Fragment implements IWalletFragmentView {
    IWalletPresenter presenter;
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.editText_shaba)
    EditText editTextShab;
    @BindView(R.id.textView_grade)
    TextView textViewScore;
    @BindView(R.id.textView_money)
    TextView textViewMoney;
    @BindView(R.id.button_submit)
    Button buttonSubmit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wallet_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new WalletFragmentPresenter(this, getContext(), progressBar, root);
        editTextShab.setText(PrefManager.getInstance().getUser().shabaNumber);
        textViewScore.setText(PrefManager.getInstance().getUser().score);
        int score = Integer.parseInt(textViewScore.getText().toString());
        score = score * 3;
        String showScore = String.valueOf(score);
        textViewMoney.setText(String.format(new Locale("fa"), "%s%s",
                showScore, " تومان "));

        presenter.checkZeroGrade();

    }
    @OnClick(R.id.button_submit)
    public void sendRequest(){
        presenter.checkValue(textViewScore.getText().toString(),editTextShab.getText().toString());

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

    @Override
    public void checkeOk() {
        int score=Integer.valueOf(textViewScore.getText().toString());
        presenter.pay(score);
    }
}
