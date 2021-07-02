package com.example.springboot.demo.service.controller;

import com.example.springboot.demo.model.CallResult;
import com.example.springboot.demo.model.UserDO;
import com.example.springboot.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaoyongqiang
 * @date 2021/7/1
 * @desc
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user/login")
    public CallResult login(@RequestBody UserDO userDO) {
        if (userDO == null) {
            return CallResult.fail(CallResult.RETURN_STATUS_PARAM_ERROR, "参数异常，请检查参数！");
        }
        return userService.login(userDO);
    }
}
