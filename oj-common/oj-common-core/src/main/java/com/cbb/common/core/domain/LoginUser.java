package com.cbb.common.core.domain;

import lombok.Data;

@Data
public class LoginUser {

    /**
     * ⽤⼾⾝份 (0: 普通⽤⼾ 1: 管理员)
     */
    private Integer identity;
}
