package com.tracer85.shop.model.request;

import lombok.Data;



@Data
public class UserDetailRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
