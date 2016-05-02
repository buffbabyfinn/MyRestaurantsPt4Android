package com.epicodus.myrestaurantsv2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.myrestaurantsv2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.restaurantButton) Button mRestaurantButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            mRestaurantButton.setOnClickListener(this);
            mAboutButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.restaurantButton:
                    String location = mLocationEditText.getText().toString();
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
}
