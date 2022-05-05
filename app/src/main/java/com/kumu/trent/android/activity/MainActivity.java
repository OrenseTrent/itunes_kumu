package com.kumu.trent.android.activity;


import com.kumu.trent.R;
import com.kumu.trent.android.fragment.main.DetailFragment;
import com.kumu.trent.android.fragment.main.HomeFragment;
import com.kumu.trent.android.route.RouteActivity;
import com.kumu.trent.data.model.api.TrackModel;

public class MainActivity extends RouteActivity {
    public static final String TAG = MainActivity.class.getName().toString();

    private TrackModel sampleModel;
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
            case "detail":
                openDetailFragment(sampleModel);
                break;
                default:
                    openHomeFragment();
                break;
        }
    }

    public void openHomeFragment(){ switchFragment(HomeFragment.newInstance()); }
    public void openDetailFragment(TrackModel sampleModel){ switchFragment(DetailFragment.newInstance(sampleModel)); }

    @Override
    public void onBackPressed() { super.onBackPressed();
    }


}
