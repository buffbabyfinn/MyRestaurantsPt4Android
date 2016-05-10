package com.epicodus.myrestaurantsv2;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.epicodus.myrestaurantsv2.ui.LoginActivity;
import com.firebase.client.Firebase;

/**
 * Created by Guest on 5/10/16.
 */
public class BaseFragment extends Fragment {
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mSharedPreferencesEditor;
    public Firebase mFirebaseRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    public void addToSharedPreferences(String location) {
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    public void logout() {
        mFirebaseRef.unauth();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}








