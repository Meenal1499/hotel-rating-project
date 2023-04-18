package com.user.service.services;

import com.user.service.dao.UserDao;
import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //generate unique user id
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User given id is not found on server "+userId));
        //fetch rating for user
        Rating[] ratingOfUser=restTemplate.getForObject("http://rating-service/ratings/users/"+user.getUserId(),Rating[].class);
        logger.info("{}",ratingOfUser);

        List<Rating> ratings=Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList= ratings.stream().map(rating -> {


//            ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://hotel-service/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel=forEntity.getBody();

            Hotel hotel=hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
