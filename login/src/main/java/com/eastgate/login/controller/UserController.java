package com.eastgate.login.controller;

import com.eastgate.login.entity.Role;
import com.eastgate.login.entity.User;

import java.util.List;

public interface UserController {
    User findById (int id);
    List<User> findAll();
    User deleteById(int id);

    void deleteAll();
    User findByUsername(String username);
    List<User> findByRoles (Role role);
    User create(User user);
    User update(int id,User user);
}
