package com.epicodus.myrestaurantsv2.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.myrestaurantsv2.Constants;
import com.epicodus.myrestaurantsv2.R;
import com.epicodus.myrestaurantsv2.models.Restaurant;
import com.epicodus.myrestaurantsv2.adapters.FirebaseRestaurantListAdapter;
import com.epicodus.myrestaurantsv2.util.OnStartDragListener;
import com.epicodus.myrestaurantsv2.util.SimpleItemTouchHelperCallback;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_restaurant_list);
    }
}
