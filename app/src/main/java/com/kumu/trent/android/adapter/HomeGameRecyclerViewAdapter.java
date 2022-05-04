package com.kumu.trent.android.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kumu.trent.R;
import com.kumu.trent.data.model.api.SampleModel;
import com.kumu.trent.vendor.android.base.BaseRecylerViewAdapter;

import butterknife.BindView;


public class HomeGameRecyclerViewAdapter extends BaseRecylerViewAdapter<HomeGameRecyclerViewAdapter.ViewHolder, SampleModel>{

    private ClickListener clickListener;

    public HomeGameRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getDefaultView(parent, R.layout.adapter_home_gamer));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(getItem(position));
        holder.adapterCON.setTag(holder.getItem());
        holder.adapterCON.setOnClickListener(this);

        holder.trackName.setText((holder.getItem().trackName));
        Glide.with(getContext()).load(holder.getItem().artworkUrl100).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(holder.trackArt);

    }

    public class ViewHolder extends BaseRecylerViewAdapter.ViewHolder{

        @BindView(R.id.trackArt)       ImageView trackArt;
        @BindView(R.id.trackName)      TextView trackName;
        @BindView(R.id.trackPrice)      TextView trackPrice;
        @BindView(R.id.trackGenre)      TextView trackGenre;
        @BindView(R.id.adapterCON)      LinearLayout adapterCON;

        public ViewHolder(View view) {
            super(view);
        }

        public SampleModel getItem() {
            return (SampleModel) super.getItem();
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(SampleModel sampleModel);
    }
} 
