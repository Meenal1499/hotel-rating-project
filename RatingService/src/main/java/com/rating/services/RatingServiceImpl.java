package com.rating.services;

import com.rating.dao.RatingDao;
import com.rating.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingDao ratingDao;
    @Override
    public Rating createRatings(Rating rating) {
        //generate unique rating id
        String randomRatingId= UUID.randomUUID().toString();
        rating.setRatingId(randomRatingId);
        return ratingDao.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
            return ratingDao.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingDao.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingDao.findByHotelId(hotelId);
    }
}
