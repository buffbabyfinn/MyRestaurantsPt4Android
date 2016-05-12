package com.epicodus.myrestaurantsv2.util;

import com.epicodus.myrestaurantsv2.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by Megan on 5/12/2016.
 */
public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants);
}
