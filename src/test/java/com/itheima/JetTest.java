package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;


public class JetTest {
    @Test
    public void testGen(){
        Map<String ,Object> claims = new java.util.HashMap<>();
        claims.put("id",1);
        claims.put("username","admin");


        //生成jwt代码
       String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))   //设置过期时间 12小时
                .sign(Algorithm.HMAC256("itheima"));//签名指定算法配置密钥


        System.out.println(token);

    }
    @Test
    public void testPass(){
        //定义字符串模拟用户传来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTc3NTUwMzEzMH0." +
                "CzTfrt3KL6NvExUHu7Ad5padU4UeafSzUYiVAI4aHvw";

      JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();

      DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token生成一个解析后的对象
       Map<String, Claim> claims =  decodedJWT.getClaims();
       System.out.println(claims.get("user"));




    }
}

