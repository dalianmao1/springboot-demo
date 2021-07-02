package com.example.springboot.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zhaoyongqiang
 * @date 2021/7/1
 * @desc
 */
@Data
public class UserDO {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date crateTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
