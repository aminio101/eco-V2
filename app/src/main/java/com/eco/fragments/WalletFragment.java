package com.eco.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eco.PrefManager;
import com.eco.R;
import com.eco.interfaces.IWalletFragmentView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.presenters.WalletFragmentPresenter;
import com.eco.views.DialogConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletFragment extends Fragment implements IWalletFragmentView {
    IWalletPresenter presenter;
    View view;
    String scoreValue;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.editText_shaba)
    EditText editTextShab;
    @BindView(R.id.textView)
    TextView textViewScore;
    @BindView(R.id.textView_money)
    TextView textViewMoney;
    @BindView(R.id.button_submit)
    Button buttonSubmit;
    @BindView(R.id.score)
    Spinner spinnerScore;

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
        textViewScore.setText(String.valueOf(PrefManager.getInstance().getUser().score));
        int score = Integer.parseInt(textViewScore.getText().toString());
        score = score * 3;
        String showScore = String.valueOf(score);
        textViewMoney.setText(String.format(new Locale("fa"), "%s%s",
                showScore, " تومان "));

        List<String> items =  new ArrayList<String>();
        items.add("۱۰۰۰");
        items.add("۲۰۰۰");
        items.add("۳۰۰۰");
        items.add("۴۰۰۰");
        items.add("۵۰۰۰");
        items.add("۱۰۰۰۰");
        items.add("۲۰۰۰۰");
        items.add("۳۰۰۰۰");
        items.add("۴۰۰۰۰");
        items.add("۵۰۰۰۰");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerScore.setAdapter(spinnerArrayAdapter);
        scoreValue= spinnerArrayAdapter.getItem(0);
        spinnerScore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                scoreValue = adapterView.getItemAtPosition(i).toString();
                int score = Integer.parseInt(scoreValue);
                score = score * 3;
                String showScore = String.valueOf(score);
                textViewMoney.setText(String.format(new Locale("fa"), "%s%s",
                        showScore, " تومان "));
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.button_submit)
    public void sendRequest() {
        presenter.checkValue(spinnerScore.getSelectedItem().toString(), editTextShab.getText().toString());
    }


    @Override
    public void success(int i) {
        textViewScore.setText(String.valueOf(i));
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
    public void checkOk() {
        presenter.pay(Integer.valueOf(spinnerScore.getSelectedItem().toString()));
    }

    @Override
    public void rGetUser() {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUser();
            }
        });
    }
}
