package com.kumu.trent.android.fragment.landing;

import android.os.Handler;

import com.kumu.trent.R;
import com.kumu.trent.android.activity.LandingActivity;
import com.kumu.trent.data.preference.UserData;
import com.kumu.trent.vendor.android.base.BaseFragment;

public class SplashFragment extends BaseFragment {
    public static final String TAG = SplashFragment.class.getName();

    private LandingActivity landingActivity;
    private Runnable runnable;
    private Handler handler;

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public void onViewReady() {
        landingActivity = (LandingActivity) getContext();

            runnable = () -> landingActivity.runOnUiThread(() -> {
            landingActivity.startMainActivity("home");
            });
            handler = new Handler();
            handler.postDelayed(runnable, 3000);

    }

    @Override
    public int onLayoutSet() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}