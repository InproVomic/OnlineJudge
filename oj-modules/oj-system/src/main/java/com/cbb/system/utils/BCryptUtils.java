package com.cbb.system.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 密码加密工具类
public class BCryptUtils {
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean matchPassword(String password, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashedPassword);
    }
}
