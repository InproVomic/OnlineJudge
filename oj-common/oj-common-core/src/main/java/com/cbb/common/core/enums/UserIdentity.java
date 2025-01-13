package com.cbb.common.core.enums;

import lombok.Getter;

@Getter
public enum UserIdentity {
    ORDINARY(2, "普通用户"),
    ADMIN(1, "管理员");


    private Integer value;
    private String desc;

    UserIdentity(Integer code, String info) {
        this.value = code;
        this.desc = info;
    }
}
