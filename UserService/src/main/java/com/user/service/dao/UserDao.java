package com.user.service.dao;

import com.user.service.entity.Rating;
import com.user.service.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {

}
