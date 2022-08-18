package com.zhao.log;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
public class HttpLog {

    private String description;

    /**
     *  请求方法 [POST,GET,....]
     */
    private String requestMethod;

    /**
     * 请求 URL 中包含的查询字符串
     */
    private String queryString;

    /**
     * 请求 URI
     */
    private String requestURI;

    /**
     * 远程ip地址
     */
    private String remoteAddr;


    @Override
    public String toString() {
        return StrUtil.format("{} - {} - {} - {}",requestMethod,requestURI,remoteAddr, description);
    }
}
