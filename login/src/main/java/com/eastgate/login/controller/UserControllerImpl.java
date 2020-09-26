package com.eastgate.login.controller;

import com.eastgate.login.entity.Role;
import com.eastgate.login.entity.User;
import com.eastgate.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController{
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public User deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("")
    public void deleteAll() {
        userService.deleteAll();
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/role/{role}")
    public List<User> findByRoles(@PathVariable Role role) {
        return userService.findByRoles(role);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        return userService.create(user.getUsername(), user.getPassword());
    }

    @PutMapping("/{id}")
    public User update(@PathVariable int id,@RequestBody User user) {
        return userService.update(id, user.getUsername(), user.getPassword());
    }
}
