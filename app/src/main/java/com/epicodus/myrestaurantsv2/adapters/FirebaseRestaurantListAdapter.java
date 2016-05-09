package com.epicodus.myrestaurantsv2.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.myrestaurantsv2.Constants;
import com.epicodus.myrestaurantsv2.R;
import com.epicodus.myrestaurantsv2.adapters.RestaurantViewHolder;
import com.epicodus.myrestaurantsv2.models.Restaurant;
import com.epicodus.myrestaurantsv2.util.FirebaseRecyclerAdapter;
import com.epicodus.myrestaurantsv2.util.ItemTouchHelperAdapter;
import com.epicodus.myrestaurantsv2.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.Collections;


/**
 * Created by Megan on 5/2/2016.
 */
public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<RestaurantViewHolder, Restaurant> implements ItemTouchHelperAdapter{
    private final OnStartDragListener mDragStartListener;
    private Context mContext;

    public FirebaseRestaurantListAdapter(Query query, Class<Restaurant> itemClass, OnStartDragListener startDragListener) {
        super(query, itemClass);
        mDragStartListener = startDragListener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.restaurant_list_item_drag, parent, false);
        return new RestaurantViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(final RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(getItem(position));
        holder.mRestaurantImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String uid = sharedPreferences.getString(Constants.KEY_UID, null);
        Firebase ref = new Firebase(Constants.FIREBASE_URL_RESTAURANTS).child(uid);
        String restaurantKey = getItem(position).getPushId();
        ref.child(restaurantKey).removeValue();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    protected void itemAdded(Restaurant item, String key, int position) {

    }

    @Override
    protected void itemChanged(Restaurant oldItem, Restaurant newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Restaurant item, String key, int position) {

    }

    @Override
    protected void itemMoved(Restaurant item, String key, int oldPosition, int newPosition) {

    }
}
