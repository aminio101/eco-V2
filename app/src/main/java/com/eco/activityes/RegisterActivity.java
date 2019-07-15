package com.eco.activityes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.eco.CoustomEditText;
import com.eco.CoustomTextView;
import com.eco.PrefManager;
import com.eco.R;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.SignupAnswerEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IRegisterView;
import com.eco.presenters.RegisterPresenter;
import com.eco.views.DialogConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {


    String mobile;

    @BindView(R.id.progress_circular)
    ProgressBar progressBar;
    @BindView(R.id.editText_name)
    CoustomEditText editTextName;
    @BindView(R.id.editText_family)
    CoustomEditText editTextFamily;
    @BindView(R.id.editText_address)
    CoustomEditText editTextAddress;
    @BindView(R.id.editText_Education)
    CoustomEditText editTexteducation;
    @BindView(R.id.editText_email)
    CoustomEditText editTextEmail;
    @BindView(R.id.editText_work)
    CoustomEditText editTextWork;
    @BindView(R.id.editText_familyNumber)
    CoustomEditText editTextFamilyNumber;
    @BindView(R.id.editText_shabaNumber)
    CoustomEditText editTextShabaNumber;
    @BindView(R.id.button_register)
    Button button_register;
    @BindView(R.id.rdb_female)
    RadioButton rdb_female;
    @BindView(R.id.rdb_male)
    RadioButton rdb_male ;
    @BindView(R.id.type)
    Switch switch_type;
    RegisterPresenter presenter;

    @BindView(R.id.textView_name)
    CoustomTextView textView_name;
    @BindView(R.id.textView_family)
    CoustomTextView textView_family;
    @BindView(R.id.textView_address)
    CoustomTextView textView_addres;
    @BindView(R.id.textView_Education)
    CoustomTextView textView_education;
    @BindView(R.id.textView_email)
    CoustomTextView textView_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }
    private void init() {

       presenter = new RegisterPresenter(this, this, progressBar,button_register);
        mobile = getIntent().getStringExtra("mobile");
        switch_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.checkedChange(isChecked);
            }
        });
    }

    @OnClick(R.id.button_register)
    public void Register()
    {
        UserEntity user = new UserEntity(new LocationEntity().setFirstLat(0).setFirstLng(0),
                10,
                408,
                1,
                mobile,
                editTextName.getText().toString(),
                editTextFamily.getText().toString(),
                editTextEmail.getText().toString(),
                editTextAddress.getText().toString(),
                editTexteducation.getText().toString(),
                editTextShabaNumber.getText().toString(),
                ((rdb_male.isChecked()) ? "1" : "2"),
                editTextFamilyNumber.getText().toString(),
                ((switch_type.isChecked()) ? 1 : 2),
                PrefManager.getInstance().getFirstToken());



        presenter.register(user);
    }

    @Override
    public void isChecked() {
        textView_name.setText("نام");
        textView_family.setText("نام خانوادگی");
        textView_addres.setText("آدرس");
        textView_education.setText("تحصیلات");
        textView_email.setText("پست الکترونیک");
    }

    @Override
    public void notChecked() {
        textView_name.setText("نوع واحد صنفی");
        textView_family.setText("نام واحد صنفی");
        textView_addres.setText("نام نماینده");
        textView_education.setText("نام خانوادگی نماینده");
        textView_email.setText("تعداد پرسنل فعال");
    }

    @Override
    public void success(SignupAnswerEntity result) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void rRegister(UserEntity user) {
        DialogConnection dialogConnection = new DialogConnection(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register(user);
            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
