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

        if (!UserData.isLogin()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    landingActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            landingActivity.openLoginFragment();
                        }
                    });
                }
            };
            handler = new Handler();
            handler.postDelayed(runnable, 3000);
        } else {
            attemptRefreshToken();
        }

    }

    @Override
    public int onLayoutSet() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
//        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void attemptRefreshToken() {
//        Auth.getDefault().refreshToken(getContext());
    }

//    @Subscribe
//    public void onResponse(Auth.RefreshTokenResponse response) {
//        try{
//            BaseTransformer baseTransformer= response.getData(BaseTransformer.class);
//            if (baseTransformer.status){
//                UserData.insert(UserData.AUTHORIZATION, baseTransformer.new_token);
//                landingActivity.startMainActivity("home");
//                Toast.makeText(landingActivity, "Welcome back, "+UserData.getUserModel().name+"!", Toast.LENGTH_SHORT).show();
//            } else {
////                Toast.makeText(landingActivity, baseTransformer.msg, Toast.LENGTH_SHORT).show();
//            }
//        }catch (NullPointerException e){
//            Log.e(TAG,e.toString());
//            ToastMessage.show(landingActivity,"Session expired! Please try to login again", ToastMessage.Status.FAILED);
//            landingActivity.openLoginFragment();
//        }
//
//    }
}