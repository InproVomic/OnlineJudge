package com.cbb.common.core.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseEntity {

    private LocalDate createTime;

    private LocalDate updateTime;

    private Long createBy;

    private Long updateBy;
}
