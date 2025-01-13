package com.cbb.system.service;

import com.cbb.common.core.domain.Result;

public interface ISysUserService {
    Result Login(String userAccount, String password);
}
