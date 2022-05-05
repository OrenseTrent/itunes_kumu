package com.kumu.trent.android.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kumu.trent.R;
import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.vendor.android.base.BaseRecylerViewAdapter;

import butterknife.BindView;


public class DetailRecyclerViewAdapter extends BaseRecylerViewAdapter<DetailRecyclerViewAdapter.ViewHolder, TrackModel>{

    private ClickListener clickListener;

    public DetailRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getDefaultView(parent, R.layout.adapter_detail));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(getItem(position));
        holder.adapterCON.setTag(holder.getItem());
        holder.adapterCON.setOnClickListener(this);

        holder.trackName.setText(holder.getItem().trackName);
        holder.trackPrice.setText(String.valueOf(holder.getItem().trackPrice));
        holder.trackGenre.setText(holder.getItem().primaryGenreName);
        holder.trackDescription.setText(holder.getItem().longDescription);
        holder.trackArtist.setText(holder.getItem().artistName);
        Glide.with(getContext()).load(holder.getItem().artworkUrl100).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.trackArt);

    }

    public class ViewHolder extends BaseRecylerViewAdapter.ViewHolder{

        @BindView(R.id.trackArt)       ImageView trackArt;
        @BindView(R.id.trackName)      TextView trackName;
        @BindView(R.id.trackPrice)      TextView trackPrice;
        @BindView(R.id.trackGenre)      TextView trackGenre;
        @BindView(R.id.trackDescription)      TextView trackDescription;
        @BindView(R.id.trackArtist)      TextView trackArtist;
        @BindView(R.id.adapterCON)      CardView adapterCON;

        public ViewHolder(View view) {
            super(view);
        }

        public TrackModel getItem() {
            return (TrackModel) super.getItem();
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(TrackModel sampleModel);
    }
} 
