package com.kumu.trent.vendor.android.base;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;

import com.kumu.trent.R;
import com.kumu.trent.config.App;
import com.kumu.trent.data.preference.Data;
import com.kumu.trent.vendor.android.java.Log;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.realm.Realm;



public class BaseApplication extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Realm.init(context);

        if(!App.debug){
            Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
            {
                @Override
                public void uncaughtException (Thread thread, Throwable e)
                {
                    Log.e("BaseApplication", e.getMessage());

                    System.gc();
                    Data.insert(Data.ERROR_COUNT, Data.getInt(Data.ERROR_COUNT) + 1);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(getString(R.string.sample_string))
                                    .setContentText("App restarted due to some error.");

                    NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    mNotifyMgr.notify(001, mBuilder.build());

//                    RouteManager.Route.with(context)
//                            .addActivityClass(LandingActivity.class)
//                            .addActivityTag("registration")
//                            .addFragmentTag("splash")
//                            .startActivity(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    System.exit(1);
                }
            });
        }

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getString(R.string.typeface_regular))
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public void dd(String message){
        dd("Default", message);
    }

    public void dd(String tag, String message){
        Log.dd(tag, message);
    }
}
