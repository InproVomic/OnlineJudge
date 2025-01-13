package com.cbb.system.controller;

import com.cbb.common.redis.service.RedisService;
import com.cbb.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/redisAddAndGet")
    public String redisAddAndGet(){
        SysUser user = new SysUser();
        user.setUserAccount("123");
        user.setUserId(15634L);
        redisService.setCacheObject("user",user);
        return redisService.getCacheObject("user",SysUser.class).toString();
    }
}
