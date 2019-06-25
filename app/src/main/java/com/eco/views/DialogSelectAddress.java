package com.eco.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.eco.R;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.interfaces.ICallBackFavoriteLocationDialog;

public class DialogSelectAddress extends Dialog {
    ICallBackFavoriteLocationDialog iCallBackFavoriteLocationDialog;
    Button button;
    EditText name;
    EditText des;
    FavoriteAddressEntity favoriteAddressEntity;

    public DialogSelectAddress(FavoriteAddressEntity favoriteAddressEntity, Context context, ICallBackFavoriteLocationDialog iCallBackFavoriteLocationDialog) {
        super(context);
        this.favoriteAddressEntity = favoriteAddressEntity;
        this.iCallBackFavoriteLocationDialog = iCallBackFavoriteLocationDialog;
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_adderss);
        init();
    }

    private void init() {
        button = findViewById(R.id.btn_address);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBackFavoriteLocationDialog.set(name.getText().toString(), des.getText().toString());
                dismiss();
            }
        });
        name = findViewById(R.id.editText_nameadres);
        des = findViewById(R.id.editText_explainadres);
        if (favoriteAddressEntity != null) {
            des.setText(favoriteAddressEntity.getDescription());
            name.setText(favoriteAddressEntity.getName());
            button.setText("ویرایش آدرس منتخب");
        }else
            button.setText("اضافه کردن آدرس منتخب");
    }
}
