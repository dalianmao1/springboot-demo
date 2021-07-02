package com.example.springboot.demo.service;

import com.example.springboot.demo.model.CallResult;
import com.example.springboot.demo.model.UserDO;

public interface UserService {
    CallResult login(UserDO userDO);
}