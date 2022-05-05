package com.kumu.trent.android.fragment.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kumu.trent.R;
import com.kumu.trent.android.activity.MainActivity;
import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.server.request.Auth;
import com.kumu.trent.vendor.android.base.BaseFragment;
import com.kumu.trent.vendor.android.java.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class DetailFragment extends BaseFragment implements View.OnClickListener{

    private MainActivity mainActivity;
    private LinearLayoutManager linearLayoutManager;

    private TrackModel sampleModel;

    @BindView(R.id.trackArt)                    ImageView trackArt;
    @BindView(R.id.activityBackBTN)             ImageView activityBackBTN;
    @BindView(R.id.trackDescription)            TextView trackDescription;
    @BindView(R.id.trackName)                   TextView trackName;
    @BindView(R.id.trackArtist)                 TextView trackArtist;
    @BindView(R.id.trackPrice)                 TextView trackPrice;
    @BindView(R.id.trackGenre)                 TextView trackGenre;
    @BindView(R.id.trackDate)                 TextView trackDate;
    @BindView(R.id.activityTitleTXT)                 TextView activityTitleTXT;
    @BindView(R.id.trackVid)                    VideoView trackVid;

    public static DetailFragment newInstance(TrackModel sampleModel) {
        DetailFragment fragment = new DetailFragment();
        fragment.sampleModel = sampleModel;
        return fragment;
    }


    @Override
    public int onLayoutSet() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onViewReady() {
        mainActivity = (MainActivity) getContext();
        Auth.getDefault().detail(getContext(), sampleModel.id);
        activityBackBTN.setOnClickListener(this);
        setupDetails();

    }


    private void setupDetails(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(sampleModel.releaseDate);
            trackDate.setText(d.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(getContext()).load(sampleModel.artworkUrl100).diskCacheStrategy(DiskCacheStrategy.ALL).into(trackArt);
        trackDescription.setText(sampleModel.longDescription);
        trackName.setText(sampleModel.trackName);
        activityTitleTXT.setText(sampleModel.trackName);
        trackArtist.setText(sampleModel.artistName);
        trackPrice.setText(sampleModel.currency + " " + sampleModel.trackPrice);
        trackGenre.setText(sampleModel.primaryGenreName);
        trackVid.setVideoPath(sampleModel.previewUrl);
        trackVid.start();

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onResponse(Auth.DetailResponse detailResponse){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.activityBackBTN:
                mainActivity.onBackPressed();
                break;
            case R.id.trackVid:

                break;
        }
    }
}
