package com.zhao.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@Data
@Configuration
@ConfigurationProperties(prefix = "base")
public class CommonConfig {

    private String serverUrl = "";

    private String bindIp = "";

    private String wgToken = "";

    /**
     * 向 IOC容器 注入 RestTemplate 类
     * RestTemplate 是 Spring 提供的一个 HTTP 请求工具
     */
    @Bean
    public RestTemplate restTemplate() {
        //StringHttpMessageConverter 完成请求报文到字符串和字符串到响应报文的转换器
        StringHttpMessageConverter m = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        System.out.println("注册 RestTemplate");
        return new RestTemplateBuilder().additionalMessageConverters(m).build();
    }

    /**
     * 向IOC容器 注入 TaskScheduler
     * 负责任务调度
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        System.out.println("注册 TaskScheduler");
        return taskScheduler;
    }
}
