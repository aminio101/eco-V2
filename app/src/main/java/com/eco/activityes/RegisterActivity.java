package com.eco.activityes;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;

import com.eco.R;
import com.eco.interfaces.IRegisterView;
import com.eco.presenters.EditProfilePresenter;
import com.eco.presenters.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {


    String mobile;

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
    @BindView(R.id.button_register)
    Button button_register;
    @BindView(R.id.rdb_female)
    RadioButton rdb_male;
//    @BindView(R.id.rdb_female)
//    RadioButton rdb_female;
    @BindView(R.id.type)
    Switch switch_type;
    RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
      //  presenter = new EditProfilePresenter(this, this, progressBar,button_register);
        switch_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });

    }
}
