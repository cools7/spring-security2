package com.iflytek.springsecurity.Service;


import com.iflytek.springsecurity.entity.User;

public interface UserService {

    User findUserByName(String name);
}
