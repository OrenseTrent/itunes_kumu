package com.kumu.trent.android.activity;

import com.kumu.trent.R;
import com.kumu.trent.android.fragment.landing.SplashFragment;
import com.kumu.trent.android.route.RouteActivity;



public class LandingActivity extends RouteActivity {
    public static final String TAG = LandingActivity.class.getName().toString();

    @Override
    public int onLayoutSet() {
        return R.layout.activity_landing;
    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void initialFragment(String activityName, String fragmentName) {
        switch (fragmentName){
            default:
                openSplashFragment();
                break;
        }
    }


    public void openSplashFragment(){ switchFragment(SplashFragment.newInstance()); }
}
