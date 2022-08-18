package com.zhao.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.alibaba.fastjson.JSON;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 * @author zhao
 */
public class JwtUtil {

    /**
     * 有效期为 60 * 60 *1000  一个小时
     */
    private static final Long JWT_TTL = 60 * 60 * 1000L;
    /**
     *  设置秘钥明文
     */
    private static final String JWT_KEY = "zyw";

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 传入对象生成默认过期时间的 jwt
     * @param object
     * @return 字符串 jwt
     */
    public static String createJWT(Object object) {
        return createJWT(JSON.toJSONString(object));
    }


    /**
     * 传入对象生成指定过期时间的jwt
     * @param object
     * @param ttlMillis 指定过期时间
     * @return 字符串 jwt
     */
    public static String createJWT(Object object, Long ttlMillis) {
        // 设置过期时间
        return createJWT(JSON.toJSONString(object), ttlMillis);
    }


    public static String createJWT(String id, Object object, Long ttlMillis) {
        // 设置过期时间
        return createJWT(id, JSON.toJSONString(object), ttlMillis);
    }

    public static <T> T parseJWT(String jwt, Class<T> clazz) throws Exception {
        Claims body = parseJWT(jwt);
        return JSON.parseObject(body.getSubject(), clazz);
    }

    private static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


    private static String createJWT(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }




    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    private static String createJWT(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    private static String createJWT(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("zyw")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

}