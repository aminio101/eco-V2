package com.eco.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class AnchoredImageView extends ImageView {
    public AnchoredImageView (Context context) {
        super(context);
    }

    public AnchoredImageView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnchoredImageView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        setTranslationY(-h/2);
    }
}
