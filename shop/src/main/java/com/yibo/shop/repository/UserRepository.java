package com.yibo.shop.repository;

import com.yibo.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    User findByName(String name);


}
