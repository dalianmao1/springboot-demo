package com.example.springboot.demo.model;

import lombok.Data;

/**
 * @author zhaoyongqiang
 * @date 2021/7/2
 * @desc
 */
@Data
public class Menu {

    private int id;

    private int val;

    private int preId;

    public Menu(int id, int val, int preId) {
        this.id = id;
        this.val = val;
        this.preId = preId;
    }


}
