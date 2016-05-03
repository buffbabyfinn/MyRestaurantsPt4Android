package com.epicodus.myrestaurantsv2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.myrestaurantsv2.Constants;
import com.epicodus.myrestaurantsv2.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mSearchedLocationRef;
    private ValueEventListener mSearchedLocationRefListener;

    @Bind(R.id.restaurantButton) Button mRestaurantButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.savedRestaurantsButton) Button mSavedRestaurantsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRestaurantButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mSavedRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restaurantButton:
                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutButton:
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.savedRestaurantsButton:
                Intent saveIntent = new Intent(MainActivity.this, SavedRestaurantListActivity.class);
                startActivity(saveIntent);
            default:
                break;
        }
    }

}
