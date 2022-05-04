package com.kumu.trent.android.activity;


import com.kumu.trent.R;
import com.kumu.trent.android.fragment.main.HomeFragment;
import com.kumu.trent.android.route.RouteActivity;

public class MainActivity extends RouteActivity {
    public static final String TAG = MainActivity.class.getName().toString();

    @Override
    public int onLayoutSet() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewReady() {

    }

    @Override
    public void initialFragment(String activityName, String fragmentName) {
        switch (fragmentName){
            case "home":
                openHomeFragment();
                break;
                default:
                    openHomeFragment();
                break;
        }
    }

    public void openHomeFragment(){ switchFragment(HomeFragment.newInstance()); }

    @Override
    public void onBackPressed() { super.onBackPressed();
    }


}
