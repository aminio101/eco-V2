package com.eco.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.eco.R;

public class DialogConnection extends Dialog {
    RelativeLayout yes,no;
    View. OnClickListener onClickListenerYes;

    public void setYesOnclick(View.OnClickListener yesOnclick) {
        this.onClickListenerYes = yesOnclick;
    }
    public DialogConnection(Activity a,View.OnClickListener onClickListenerYes) {
        super(a);
        this.onClickListenerYes = onClickListenerYes;
        show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rety_dialog);
        setCancelable(false);
        yes =  findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
        yes.setOnClickListener(yesOnclick);
        no.setOnClickListener(noOnclick);
    }



    View.OnClickListener noOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
    View.OnClickListener yesOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            onClickListenerYes.onClick(v);
        }
    };

}
