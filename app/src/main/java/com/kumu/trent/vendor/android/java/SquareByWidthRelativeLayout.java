package com.kumu.trent.vendor.android.java;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by BCTI 3 on 9/9/2016.
 */
public class SquareByWidthRelativeLayout extends RelativeLayout {

    public SquareByWidthRelativeLayout(Context context) {
        super(context);
    }
    public SquareByWidthRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareByWidthRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
