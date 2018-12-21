package com.leo.testspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

    }
}
