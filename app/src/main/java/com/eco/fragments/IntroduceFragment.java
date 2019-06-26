package com.eco.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.eco.R;
import com.eco.interfaces.IIntroducePresenter;
import com.eco.interfaces.IIntroduceView;
import com.eco.interfaces.IWalletPresenter;
import com.eco.presenters.IntroducePresenter;
import com.eco.presenters.WalletFragmentPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class IntroduceFragment extends Fragment implements IIntroduceView {
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.editText2)
    EditText name;
    @BindView(R.id.editText3)
    EditText phone;
    IIntroducePresenter presenter;
    @OnClick(R.id.editText2)public void onNameClick(){
        name.setBackgroundResource(R.drawable.custom_shaba_edit_text_orange);
    }
    @OnClick(R.id.editText3)public void onPhoneClick(){
        phone.setBackgroundResource(R.drawable.custom_shaba_edit_text_orange);
    }
    @OnClick(R.id.button2)
    public void introduce() {
        presenter.invite(phone.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.introduction_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new IntroducePresenter(this, getContext(), progressBar, root);
    }

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void success() {
        phone.setText("");
        name.setText("");
        phone.setBackgroundResource(R.drawable.custom_shaba_edit_text_gray);
        name.setBackgroundResource(R.drawable.custom_shaba_edit_text_gray);
    }

    @Override
    public void rInvite(String phone) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.invite(phone);
            }
        });
    }
}
