package com.zhao.client.utils;


import cn.hutool.json.JSONObject;
import com.zhao.client.config.CommonConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RestUtilTest {

    @Autowired
    RestUtil restUtil = new RestUtil();

    @Autowired
    private CommonConfig config;


    @Test
    public void testPostWithoutJSONObject() {}{
        JSONObject response = restUtil.post(config.getServerUrl() + "client/post");
        System.out.println(response);
    }

    @Test
    public void testPostWithJSONObject() {
        JSONObject jsonObject = new JSONObject();
        String responseBody = restUtil.post(config.getServerUrl() + "client/post", jsonObject);
        System.out.println(responseBody);
    }


}