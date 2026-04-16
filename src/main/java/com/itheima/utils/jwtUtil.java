package com.itheima.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import java.util.Date;
import java.util.Map;

public class jwtUtil {
    private static final String KEY = "woaini";
    public static String getToken(Map<String,Object>  claims){
       return JWT.create()
                .withClaim("claims",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))   //设置过期时间 12小时
                .sign(Algorithm.HMAC256(KEY));//签名指定算法配置密钥

    }
    //接收token，验证 token，返回结果
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
