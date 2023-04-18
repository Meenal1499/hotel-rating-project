package com.hotel.dao;

import com.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDao extends JpaRepository<Hotel, String> {
}
