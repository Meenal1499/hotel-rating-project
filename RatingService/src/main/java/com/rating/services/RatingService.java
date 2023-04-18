package com.rating.services;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating createRatings(Rating rating);

    //get all
    List<Rating> getRatings();

    //get all by user id
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel id
    List<Rating> getRatingByHotelId(String hotelId);
}
