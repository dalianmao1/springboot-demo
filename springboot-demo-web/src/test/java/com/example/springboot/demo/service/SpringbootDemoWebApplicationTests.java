package com.example.springboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.demo.model.CallResult;
import com.example.springboot.demo.model.Menu;
import com.example.springboot.demo.model.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SpringbootDemoWebApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static UserDO userDO;

    /**
     * 节点集合
     */
    private static final List<Menu> list = new ArrayList<>();

    /**
     * 用于查找上一个节点
     */
    private static final Map<String, Menu> map = new HashMap<>();

    @BeforeAll
    static void beforLoginTest() {
        userDO = new UserDO();
        userDO.setMobile("17612345678");
        userDO.setPassword("123456");
    }

    @Test
    void loginInterfaceTest() throws Exception {

        //验证测试用例是否创建
        Assertions.assertNotNull(userDO, "userDO is null");

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(userDO)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();
        //验证http状态码
        Assertions.assertNotEquals(MockMvcResultMatchers.status().isOk(), response.getStatus());
        CallResult userResponse = JSONObject.parseObject(response.getContentAsString(), CallResult.class);
        //验证业务状态码
        Assertions.assertEquals(userResponse.getCode(), CallResult.RETURN_STATUS_OK);

        UserDO userResult = JSONObject.parseObject(userResponse.getContent(), UserDO.class);
        Assertions.assertEquals(userDO.getMobile(), userResult.getMobile());
        log.info("[测试通过]");
    }

    @Test
    void assembleData() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/config.txt"));
        //递增id
        int index = 0;
        //读取文件的每行数据
        String line;
        //节点
        Menu menu;
        while ((line = br.readLine()) != null) {

            String[] menuArr = line.split("&");
            for (int i = 0; i < menuArr.length; i++) {

                int val = Integer.parseInt(menuArr[i]);
                //当前节点key
                String key = i + "_" + val;
                //判断是否有前一个节点并赋值
                Menu pre = i - 1 >= 0 ? map.get((i - 1) + "_" + menuArr[i - 1]) : null;
                //如果已经添加过此节点则不再添加
                if (map.containsKey(key)) continue;
                if (null == pre) {
                    menu = new Menu(index++, i, val, -1);
                } else {
                    menu = new Menu(index++, i, val, pre.getId());
                }
                //map 与 list 都能提供查找
                //存入map
                map.put(key, menu);
                //存入list
                list.add(menu);
            }
        }
        List<Menu> menuByVal = getMenuById(-1);
        System.out.println(JSONObject.toJSONString(menuByVal));
    }

    @Test
    List<Menu> getMenuById(int id) throws Exception {
        List<Menu> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Menu menu = list.get(i);
            if (menu.getPreId() == id) {
                result.add(menu);
            }
        }
        return result;
    }
}
