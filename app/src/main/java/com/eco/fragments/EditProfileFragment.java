package com.eco.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eco.PrefManager;
import com.eco.R;
import com.eco.entitys.RequstUserUpdateEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IEditProfileView;
import com.eco.presenters.EditProfilePresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileFragment extends Fragment implements IEditProfileView {
    View view;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.editText_name)
    EditText editTextName;
    @BindView(R.id.editText_family)
    EditText editTextFamily;
    @BindView(R.id.editText_address)
    EditText editTextAddress;
    @BindView(R.id.editText_Education)
    EditText editTexteducation;
    @BindView(R.id.editText_email)
    EditText editTextEmail;
    @BindView(R.id.editText_work)
    EditText editTextWork;
    @BindView(R.id.editText_familyNumber)
    EditText editTextFamilyNumber;
    @BindView(R.id.editText_shabaNumber)
    EditText editTextShabaNumber;
    @BindView(R.id.button_submit)
    Button button_submit;

    RequstUserUpdateEntity requstUserUpdate;

    EditProfilePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.edite_profile_fargment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @OnClick(R.id.button_submit)
    public void update() {
        presenter.checkValue(editTextAddress,editTextName,editTextFamily);
    }

    void init(){
        editTextName.setText(PrefManager.getInstance().getUser().name);
        editTextFamily.setText(PrefManager.getInstance().getUser().family);
        editTextAddress.setText(PrefManager.getInstance().getUser().address);
        editTextEmail.setText(PrefManager.getInstance().getUser().email);
        editTexteducation.setText(PrefManager.getInstance().getUser().grade);
        editTextWork.setText(PrefManager.getInstance().getUser().work);//add work to user
        editTextFamilyNumber.setText(PrefManager.getInstance().getUser().familyNumber);
        editTextShabaNumber.setText(PrefManager.getInstance().getUser().shabaNumber);

        presenter = new EditProfilePresenter(this, getContext(), progressBar, button_submit);


    }
    @Override
    public void success(UserEntity result) {
        PrefManager.getInstance().setUser(result);
    }

    @Override
    public void rupdate(RequstUserUpdateEntity requstUserUpdateEntity) {
        DialogConnection dialogConnection = new DialogConnection(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkValue(editTextAddress,editTextName,editTextFamily);
            }
        });
    }

    @Override
    public void callHome() {
        Intent callHome = new Intent("ECO.UPDATE");
        getActivity().sendBroadcast(callHome);
    }


    @Override
    public void checkedOk() {
        requstUserUpdate = new RequstUserUpdateEntity();
        requstUserUpdate.name = editTextName.getText().toString();
        requstUserUpdate.provinceId = PrefManager.getInstance().getUser().provinceId;
        requstUserUpdate.cityId = PrefManager.getInstance().getUser().cityId;
        requstUserUpdate.countryId = PrefManager.getInstance().getUser().countryId;
        requstUserUpdate.family = editTextFamily.getText().toString();
        requstUserUpdate.email = editTextEmail.getText().toString();
        requstUserUpdate.address = editTextAddress.getText().toString();
        requstUserUpdate.grade = editTexteducation.getText().toString();
        requstUserUpdate.shabaNumber = editTextShabaNumber.getText().toString();
        requstUserUpdate.gender = String.valueOf(PrefManager.getInstance().getUser().gender);
        requstUserUpdate.familyNumber = editTextFamilyNumber.getText().toString();
        requstUserUpdate.mobile = PrefManager.getInstance().getUser().mobileNumber;//add mobile number

        presenter.update(requstUserUpdate);
    }


}
