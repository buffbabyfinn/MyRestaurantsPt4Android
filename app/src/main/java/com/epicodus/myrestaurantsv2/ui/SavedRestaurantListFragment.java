package com.epicodus.myrestaurantsv2.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.myrestaurantsv2.BaseFragment;
import com.epicodus.myrestaurantsv2.Constants;
import com.epicodus.myrestaurantsv2.R;
import com.epicodus.myrestaurantsv2.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurantsv2.models.Restaurant;
import com.epicodus.myrestaurantsv2.util.OnStartDragListener;
import com.epicodus.myrestaurantsv2.util.SimpleItemTouchHelperCallback;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedRestaurantListFragment extends BaseFragment implements OnStartDragListener{
    private Query mQuery;
    private Firebase mFirebaseRestaurantsRef;
    private FirebaseRestaurantListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;


    public SavedRestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseRestaurantsRef = new Firebase(Constants.FIREBASE_URL_RESTAURANTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_restaurant_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseQuery();
        setUpRecyclerView();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        String uid = mSharedPreferences.getString(Constants.KEY_UID, null);
        for (Restaurant restaurant : mAdapter.getItems()) {
            String pushId = restaurant.getPushId();
            restaurant.setIndex(Integer.toString(mAdapter.getItems().indexOf(restaurant)));
            mFirebaseRestaurantsRef.child(uid)
                    .child(pushId)
                    .setValue(restaurant);
        }
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String location = mFirebaseRestaurantsRef.child(userUid).toString();
        mQuery = new Firebase(location).orderByChild("index");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseRestaurantListAdapter(mQuery, Restaurant.class, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
