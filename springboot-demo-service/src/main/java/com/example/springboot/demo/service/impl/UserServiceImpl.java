package com.example.springboot.demo.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.example.springboot.demo.dao.UserMapper;
import com.example.springboot.demo.model.CallResult;
import com.example.springboot.demo.model.UserDO;
import com.example.springboot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author zhaoyongqiang
 * @date 2021/7/1
 * @desc
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Mockito使用注解注入依赖关系，需提供构造器
     *
     * @param userMapper
     */

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public CallResult login(UserDO userDO) {
        UserDO userEntity = userMapper.selectByMobile(userDO.getMobile());
        if (userEntity == null) {
            log.info("没有该用户信息，请先注册！");
            return CallResult.fail(CallResult.RETURN_STATUS_UNREGISTERED, "没有该用户信息，请先注册！");
        }
        if (!userDO.getPassword().equals(userEntity.getPassword())) {
            return CallResult.fail(CallResult.RETURN_STATUS_PASW_INCORRECT, "您的密码不正确！");
        }
        return CallResult.success(CallResult.RETURN_STATUS_OK, "登录成功！", JSONObject.toJSONString(userEntity));
    }
}
