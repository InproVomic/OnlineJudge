package com.cbb.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cbb.common.core.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("tb_sys_user")
public class SysUser extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)//使用雪花算法生成ID
    private Long userId;

    private String userAccount;

    private String password;
}
