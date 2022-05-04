package com.kumu.trent.vendor.android.java;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by BCTI 3 on 9/9/2016.
 */
public class SquareByWidthTextView extends AppCompatTextView {

    public SquareByWidthTextView(Context context) {
        super(context);
    }
    public SquareByWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareByWidthTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
