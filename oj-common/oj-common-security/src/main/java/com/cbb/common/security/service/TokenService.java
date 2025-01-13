package com.cbb.common.security.service;

import cn.hutool.core.lang.UUID;
import com.cbb.common.core.constants.CacheConstants;
import com.cbb.common.core.constants.JwtConstants;
import com.cbb.common.core.domain.LoginUser;
import com.cbb.common.redis.service.RedisService;
import com.cbb.common.core.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TokenService {

    @Autowired
    private RedisService redisService;

    public String createToken(Long userId, String secret, Integer identity){
        Map<String,Object> claims = new HashMap<>();
        String userKey = UUID.fastUUID().toString();
        claims.put(JwtConstants.LOGIN_USER_ID,userId);
        claims.put(JwtConstants.LOGIN_USER_KEY,userKey);
        String token = JwtUtils.createToken(claims,secret);
        LoginUser loginUser = new LoginUser();
        loginUser.setIdentity(identity);
        //在redis中存储token
        redisService.setCacheObject(CacheConstants.LOGIN_TOKEN_KEY+userKey,loginUser,CacheConstants.EXPIRATION, TimeUnit.MINUTES);
        return token;
    }

    public Claims getClaims(String token, String secret) {
        Claims claims;
        try {
            claims = JwtUtils.parseToken(token, secret); //获取令牌中信息  解析payload中信息  存储着用户唯一标识信息
            if (claims == null) {
                log.error("解析token：{}, 出现异常", token);
                return null;
            }
        } catch (Exception e) {
            log.error("解析token：{}, 出现异常", token, e);
            return null;
        }
        return claims;
    }

    public Long getUserId(Claims claims) {
        if (claims == null) return null;
        return Long.valueOf(JwtUtils.getUserId(claims));  //获取jwt中的key
    }

    public String getUserKey(Claims claims) {
        if (claims == null) return null;
        return JwtUtils.getUserKey(claims);  //获取jwt中的key
    }

    private String getUserKey(String token, String secret) {
        Claims claims = getClaims(token, secret);
        if (claims == null) return null;
        return JwtUtils.getUserKey(claims);  //获取jwt中的key
    }

    public void extendToken(Claims claims) {
        String userKey = getUserKey(claims);
        if (userKey == null) {
            return;
        }
        String tokenKey = getTokenKey(userKey);

        //720min  12个小时      剩余  180min 时候对它进行延长
        Long expire = redisService.getExpire(tokenKey, TimeUnit.MINUTES);
        if (expire != null && expire < CacheConstants.REFRESH_TIME) {
            redisService.expire(tokenKey, CacheConstants.EXP, TimeUnit.MINUTES);
        }
    }

    private String getTokenKey(String userKey) {
        return CacheConstants.LOGIN_TOKEN_KEY + userKey;
    }
}
