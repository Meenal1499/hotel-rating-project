package com.hotel.services;

import com.hotel.dao.HotelDao;
import com.hotel.entity.Hotel;
import com.hotel.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelDao hotelDao;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelDao.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel given id is not found on server "+id));
    }
}
