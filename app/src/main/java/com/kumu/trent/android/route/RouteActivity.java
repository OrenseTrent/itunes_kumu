package com.kumu.trent.android.route;

import android.content.Intent;

import com.kumu.trent.android.activity.LandingActivity;
import com.kumu.trent.android.activity.MainActivity;
import com.kumu.trent.vendor.android.base.BaseActivity;
import com.kumu.trent.vendor.android.base.RouteManager;


public class RouteActivity extends BaseActivity {

    public void startMainActivity(String fragmentTAG){
        RouteManager.Route.with(this)
                .addActivityClass(MainActivity.class)
                .addActivityTag("main")
                .addFragmentTag(fragmentTAG)
                .startActivity(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
    }


    public void startLandingActivity(String fragmentTAG){
        RouteManager.Route.with(this)
                .addActivityClass(LandingActivity.class)
                .addActivityTag("landing")
                .addFragmentTag(fragmentTAG)
                .startActivity(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }



//    @Subscribe
//    public void invalidToken(InvalidToken invalidToken){
//        Log.e("Token", "Expired");
//        EventBus.getDefault().unregister(this);
//        UserData.insert(new UserModel());
//        UserData.insert(UserData.TOKEN_EXPIRED, true);
//        startLandingActivity();
//    }
}
