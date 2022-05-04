package com.kumu.trent.android.fragment.main;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kumu.trent.R;
import com.kumu.trent.android.activity.MainActivity;
import com.kumu.trent.android.adapter.HomeGameRecyclerViewAdapter;
import com.kumu.trent.data.model.api.SampleModel;
import com.kumu.trent.server.request.Auth;
import com.kumu.trent.vendor.android.base.BaseFragment;
import com.kumu.trent.vendor.android.java.EndlessRecyclerViewScrollListener;
import com.kumu.trent.vendor.android.java.Log;
import com.kumu.trent.vendor.server.transformer.CollectionTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements HomeGameRecyclerViewAdapter.ClickListener, View.OnClickListener,EndlessRecyclerViewScrollListener.Callback{
    public static final String TAG = HomeFragment.class.getName().toString();

    private MainActivity mainActivity;
    private HomeGameRecyclerViewAdapter homeGameRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    @BindView(R.id.gameRV)                  RecyclerView gameRV;
    @BindView(R.id.searchET)                EditText searchET;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public int onLayoutSet() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewReady() {
        mainActivity = (MainActivity) getContext();

        setUpListView();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = searchET.getText().toString();
//                if(s.length() != 0){
//                    Auth.getDefault().search(getContext(), searchET.getText().toString(),"au","movie","");
//                }

                if(!searchText.isEmpty()){
                    gameRV.setVisibility(View.VISIBLE);
                    Auth.getDefault().search(getContext(), searchET.getText().toString(),"au","movie","");
                }
                else{
                    gameRV.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUpListView(){
        homeGameRecyclerViewAdapter = new HomeGameRecyclerViewAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager, this);
        gameRV.setLayoutManager(linearLayoutManager);
        gameRV.setAdapter(homeGameRecyclerViewAdapter);
        gameRV.addOnScrollListener(endlessRecyclerViewScrollListener);
        gameRV.setOnClickListener(this);

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
    public void onResponse(Auth.SearchResponse searchResponse){
        try{
            CollectionTransformer<SampleModel> collectionTransformer = searchResponse.getData(CollectionTransformer.class);
//                ToastMessage.show(getContext(), "Login Success", ToastMessage.Status.SUCCESS);
            if(searchResponse.isNext()){
                homeGameRecyclerViewAdapter.addNewData(collectionTransformer.results);
                Log.d("RESULTS", collectionTransformer.results);
            }
            else{
                homeGameRecyclerViewAdapter.setNewData(collectionTransformer.results);
            }

        } catch(Exception e){
//            ToastMessage.show(getContext(), "Verification failed", ToastMessage.Status.FAILED);

        }
    }

    @Override
    public void onItemClick(SampleModel sampleModel) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) {

    }
}
