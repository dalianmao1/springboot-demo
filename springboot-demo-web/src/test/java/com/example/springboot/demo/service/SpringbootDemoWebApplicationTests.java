package com.example.springboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.demo.model.CallResult;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SpringbootDemoWebApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    static UserDO userDO;

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

}
