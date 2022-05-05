package com.kumu.trent.android.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.kumu.trent.R;
import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.vendor.android.base.BaseRecylerViewAdapter;

import java.util.List;

import butterknife.BindView;


public class HomeRecyclerViewAdapter extends BaseRecylerViewAdapter<HomeRecyclerViewAdapter.ViewHolder, TrackModel>{

    private ClickListener clickListener;
    List<TrackModel> trackdbModels;

    public HomeRecyclerViewAdapter(Context context,List<TrackModel> trackdbModels) {
        super(context);
        this.trackdbModels = trackdbModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getDefaultView(parent, R.layout.adapter_home));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(getItem(position));
        holder.adapterCON.setTag(holder.getItem());
        holder.adapterCON.setOnClickListener(this);
        holder.trackName.setText(holder.getItem().trackName);
        holder.trackPrice.setText(String.valueOf(holder.getItem().currency + " " + holder.getItem().trackPrice));
        holder.trackGenre.setText(holder.getItem().primaryGenreName);
        Glide.with(getContext()).load(holder.getItem().artworkUrl100).placeholder(R.drawable.app_logo).into(holder.trackArt);


    }

    public class ViewHolder extends BaseRecylerViewAdapter.ViewHolder{

        @BindView(R.id.trackArt)       ImageView trackArt;
        @BindView(R.id.trackName)      TextView trackName;
        @BindView(R.id.trackPrice)      TextView trackPrice;
        @BindView(R.id.trackGenre)      TextView trackGenre;
        @BindView(R.id.adapterCON)      CardView adapterCON;

        public ViewHolder(View view) {
            super(view);
            adapterCON.setOnClickListener(HomeRecyclerViewAdapter.this);
        }

        public TrackModel getItem() {
            return (TrackModel) super.getItem();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adapterCON:
                if (clickListener != null){
                    clickListener.onItemClick((TrackModel) v.getTag());
                }
                break;
        }
    }

    public void getAllDatas(List<TrackModel> trackdbModels)
    {
        this.trackdbModels = trackdbModels;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(TrackModel sampleModel);
    }

}
