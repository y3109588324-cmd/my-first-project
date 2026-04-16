package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest//这个注解的作用是在测试开始之前会先初始化spring容器

public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public  void tests(){
        //注入
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username","张三");

    }
}
