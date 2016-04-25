package com.epicodus.myrestaurantsv2;

import android.widget.TextView;

import com.epicodus.myrestaurantsv2.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Guest on 4/20/16.
 */
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView appNameTextView = (TextView) activity.findViewById(R.id.appNameTextView);
        assertTrue("MyRestaurants".equals(appNameTextView.getText().toString()));
    }
}
