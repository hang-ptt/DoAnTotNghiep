package com.tracer85.shop.controller;

import com.tracer85.shop.model.dto.UserDto;
import com.tracer85.shop.model.request.UserDetailRequestModel;
import com.tracer85.shop.model.response.UserRest;
import com.tracer85.shop.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "users"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetail){
        UserRest userRest = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);

        UserDto createdUser = userService.createdUser(userDto);
        BeanUtils.copyProperties(createdUser,userRest);
        return userRest;
    }
}
