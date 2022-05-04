package com.kumu.trent.vendor.android.java;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.kumu.trent.R;


/**
 * Created by BCTI05 on 8/4/2017.
 */

public class RoundedLinearLayout extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = RoundedLinearLayout.class.getSimpleName();
    private final Path mPath = new Path();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RoundedLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RoundedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedLinearLayout(Context context) {
        super(context);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
//        setBackgroundResource(R.drawable.bg_rounded_background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath.reset();
        float round = getResources().getDimension(R.dimen.dimen_8);
        mPath.addRoundRect(new RectF(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom()), round, round, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
    }
}
