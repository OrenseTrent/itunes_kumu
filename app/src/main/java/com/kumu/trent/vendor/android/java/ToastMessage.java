package com.kumu.trent.vendor.android.java;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kumu.trent.R;


/**
 * Created by BCTI 3 on 10/26/2016.
 */

public class ToastMessage {

    private static Toast customToast;

    public enum Status{
        SUCCESS,
        FAILED
    }
    
    public static void show(Context context, String message, Status status){
        Activity activity = (Activity)context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message, null);

        TextView messageTXT = (TextView) layout.findViewById(R.id.messageTXT);
        messageTXT.setText(message);

        View background = layout.findViewById(R.id.backgroundCON);
        ImageView iconIV = (ImageView) layout.findViewById(R.id.iconIV);

        switch (status){
            case SUCCESS:
                background.setBackground(ActivityCompat.getDrawable(activity.getBaseContext(),R.drawable.bg_toast_success));
                iconIV.setImageDrawable(ActivityCompat.getDrawable(activity.getBaseContext(),R.drawable.icon_success));
                break;
            case FAILED:
                background.setBackground(ActivityCompat.getDrawable(activity.getBaseContext(),R.drawable.bg_toast_failed));
                iconIV.setImageDrawable(ActivityCompat.getDrawable(activity.getBaseContext(),R.drawable.icon_failed));
                break;
        }

        iconIV.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        if (customToast != null) {
            customToast.cancel();
            customToast = null;
        }

        customToast = new Toast(activity.getBaseContext());
        customToast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.setView(layout);
        customToast.show();
    }

    public static void cancel(){
        if (customToast != null) {
            customToast.cancel();
            customToast.getView().setVisibility(View.GONE);
            customToast = null;
        }
    }
}
