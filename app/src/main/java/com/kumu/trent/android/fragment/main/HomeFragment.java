package com.kumu.trent.android.fragment.main;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kumu.trent.R;
import com.kumu.trent.android.activity.MainActivity;
import com.kumu.trent.android.adapter.HomeRecyclerViewAdapter;
import com.kumu.trent.data.model.TrackViewModel;
import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.data.repository.Repository;
import com.kumu.trent.server.request.Auth;
import com.kumu.trent.vendor.android.base.BaseFragment;
import com.kumu.trent.vendor.server.transformer.CollectionTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements HomeRecyclerViewAdapter.ClickListener{
    public static final String TAG = HomeFragment.class.getName().toString();

    private MainActivity mainActivity;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Repository repository;
    private List<TrackModel> getTracks;
    private TrackViewModel catViewModel;


    @BindView(R.id.homeRV)                  RecyclerView homeRV;
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
        Auth.getDefault().search(getContext(), "star","au","movie","");
        setUpListView();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = searchET.getText().toString();
                if(!searchText.isEmpty()){
                    Auth.getDefault().search(getContext(), searchET.getText().toString(),"au","movie","");
                }
                else{

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void setUpListView(){
        repository=new Repository(mainActivity.getApplication());
        getTracks = new ArrayList<>();
        catViewModel=new ViewModelProvider(this).get(TrackViewModel.class);
        homeRecyclerViewAdapter=new HomeRecyclerViewAdapter(getContext(), getTracks);
        linearLayoutManager = new LinearLayoutManager(getContext());
        catViewModel.getAllTracks().observe(this, tracks -> {
            homeRV.setLayoutManager(linearLayoutManager);
            homeRV.setAdapter(homeRecyclerViewAdapter);

            homeRecyclerViewAdapter.setStateRestorationPolicy(PREVENT_WHEN_EMPTY);
            homeRecyclerViewAdapter.getAllDatas(tracks);
//            homeRecyclerViewAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
//            Log.d("main", "onChanged: "+ tracks);
        });


        homeRecyclerViewAdapter.setClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        internetAccess();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Subscribe
    public void onResponse(Auth.SearchResponse searchResponse){
        try{
            CollectionTransformer<TrackModel> collectionTransformer = searchResponse.getData(CollectionTransformer.class);
//                ToastMessage.show(getContext(), "Login Success", ToastMessage.Status.SUCCESS);
            if(searchResponse.isNext()){
                homeRecyclerViewAdapter.addNewData(collectionTransformer.results);
            }
            else{
                homeRecyclerViewAdapter.setNewData(collectionTransformer.results);
            }
            repository.insert(collectionTransformer.results);

        } catch(Exception e){

        }
    }


    @Override
    public void onItemClick(TrackModel sampleModel) {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        mainActivity.openDetailFragment(sampleModel);
    }

    public void internetAccess(){
        //detect internet and show the data
        if(isNetworkStatusAvialable (getContext())) {
            searchET.getText().clear();
//            Toast.makeText(getContext(), "Internet detected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }

    //check internet connection
    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }


}
