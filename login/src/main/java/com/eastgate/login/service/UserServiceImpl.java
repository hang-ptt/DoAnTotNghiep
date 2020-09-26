package com.eastgate.login.service;

import com.eastgate.login.entity.Role;
import com.eastgate.login.entity.User;
import com.eastgate.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;



    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User deleteById(int id) {
        return userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByRoles(Role role) {
        return userRepository.findByRoles(role);
    }

    @Override
    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(int id, String username, String password) {
        User user = userRepository.findById(id);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }
}
