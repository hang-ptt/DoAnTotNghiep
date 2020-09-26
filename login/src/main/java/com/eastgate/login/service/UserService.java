package com.eastgate.login.service;

import com.eastgate.login.entity.Role;
import com.eastgate.login.entity.User;

import java.util.List;

public interface UserService {
    User findById (int id);
    List<User> findAll();
    User deleteById(int id);

    void deleteAll();
    User findByUsername(String username);
    List<User> findByRoles (Role role);
    User create(String username, String password);
    User update(int id, String username, String password);

}
