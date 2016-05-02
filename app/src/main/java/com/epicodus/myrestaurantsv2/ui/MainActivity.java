package com.epicodus.myrestaurantsv2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @Bind(R.id.restaurantButton) Button mRestaurantButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

//            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//            mEditor = mSharedPreferences.edit();

            mSearchedLocationRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_LOCATION);
            mRestaurantButton.setOnClickListener(this);
            mAboutButton.setOnClickListener(this);

            mSearchedLocationRefListener = mSearchedLocationRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String locations = dataSnapshot.getValue().toString();
                    Log.d("Location updated", locations);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mSearchedLocationRef.removeEventListener(mSearchedLocationRefListener);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.restaurantButton:
                    String location = mLocationEditText.getText().toString();
                    saveLocationToFirebase(location);
//                    if(!(location).equals("")) {
//                        addToSharedPreferences(location);
//                    }
                    Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    break;
                case R.id.aboutButton:
                    Intent intention = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intention);
                    break;
                default:
                    break;
            }
        }

//      private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//      }

    private void saveLocationToFirebase(String location) {
        Firebase searchedLocationRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_LOCATION);
        searchedLocationRef.push().setValue(location);
    }
}
