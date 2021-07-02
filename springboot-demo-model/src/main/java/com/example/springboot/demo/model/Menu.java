package com.example.springboot.demo.model;

import lombok.Data;

/**
 * @author zhaoyongqiang
 * @date 2021/7/2
 * @desc
 */
@Data
public class Menu {

    /**
     * 唯一id
     */
    private int id;

    /**
     * 层级
     */
    private int level;

    /**
     * 值
     */
    private int val;

    /**
     * 上一个ID
     */
    private int preId;

    public Menu() {
    }

    public Menu(int id, int level, int val, int preId) {
        this.id = id;
        this.level = level;
        this.val = val;
        this.preId = preId;
    }
}
