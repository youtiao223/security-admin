package com.zhao.client.utils;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhao.client.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtil {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CommonConfig commonConfig;

    public String post(String url, JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONUtil.parse(jsonObject).toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public JSONObject post(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

    public JSONObject get(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return JSONUtil.parseObj(responseEntity.getBody());
    }

}
