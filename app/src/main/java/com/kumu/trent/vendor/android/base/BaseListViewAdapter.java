package com.kumu.trent.vendor.android.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kumu.trent.vendor.android.java.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseListViewAdapter<VH extends BaseListViewAdapter.ViewHolder, DI extends AndroidModel>
        extends BaseAdapter
        implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<DI> data;
    private LayoutInflater layoutInflater;

    public BaseListViewAdapter(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setNewData(List<DI> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addNewData(List<DI> data){
        for(DI defaultItem : data){
            if(isDataUnique(defaultItem)){
                this.data.add(defaultItem);
            }
        }
        notifyDataSetChanged();
    }

    public void removeItem(DI item) {
        for (DI defaultItem : data) {
            if (item.id == defaultItem.id) {
                int pos = data.indexOf(defaultItem);
                data.remove(pos);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void updateItem(DI item){
        for(DI defaultItem : data){
            if (item.id == defaultItem.id) {
                int pos = data.indexOf(defaultItem);
                data.set(pos, item);
                notifyDataSetChanged();
                break;
            }
        }
    }

    private boolean isDataUnique(DI item){
        for(DI defaultItem : this.data){
            if(item.id == defaultItem.id){
                return false;
            }
        }
        return true;
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public List<DI> getData() {
        return data;
    }

    public DI getItem(int position){
        return getData().get(position);
    }

    public View getDefaultView(ViewGroup parent, int resLayout){
        return getLayoutInflater().inflate(resLayout, parent, false);
    }

    @Override
    public int getCount() {
        return getData().size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    public VH onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH viewHolder;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent);
            convertView = viewHolder.getBaseView();
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (VH) convertView.getTag();
        }

        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {

        return false;
    }

    public class ViewHolder{
        private Object object;
        private View view;
        private AndroidModel androidModel;

        public ViewHolder(View view) {
            this.view = view;
            bind(this, view);
        }

        public ViewHolder(View view, boolean bind) {
            this.view = view;
            if(bind){
                bind(this, view);
            }
        }

        public Unbinder bind(Object target, View view){
            return ButterKnife.bind(target, view);
        }

        public View getBaseView() {
            return view;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public void setItem(AndroidModel androidModel) {
            this.androidModel = androidModel;
        }

        public AndroidModel getItem() {
            return androidModel;
        }
    }

    public void dd(String message){
        dd("Default", message);
    }

    public void dd(String tag, String message){
        Log.dd(tag, message);
    }
}
