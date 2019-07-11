package com.eco.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eco.R;
import com.eco.interfaces.IIntroducePresenter;
import com.eco.interfaces.IIntroduceView;
import com.eco.presenters.IntroducePresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.button2)
    public void introduce() {
        presenter.invite(phone.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.introduction_fragment, container, false);
        hideKeyboard();
        init();
        return view;
    }

    private void init() {
        ButterKnife.bind(this, view);
        presenter = new IntroducePresenter(this, getContext(), progressBar, root);
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    name.setBackgroundResource(R.drawable.custom_shaba_edit_text_orange);
                else
                    name.setBackgroundResource(R.drawable.custom_shaba_edit_text_gray);


            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    phone.setBackgroundResource(R.drawable.custom_shaba_edit_text_orange);
                else
                    phone.setBackgroundResource(R.drawable.custom_shaba_edit_text_gray);
            }
        });
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
    void hideKeyboard(){
        View view1 = getActivity().getCurrentFocus();
        if (view1 != null){
            InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
