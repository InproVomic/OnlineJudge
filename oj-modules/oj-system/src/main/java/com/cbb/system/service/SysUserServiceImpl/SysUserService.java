package com.cbb.system.service.SysUserServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cbb.common.core.domain.Result;
import com.cbb.common.core.enums.ResultCode;
import com.cbb.common.core.enums.UserIdentity;
import com.cbb.common.security.service.TokenService;
import com.cbb.system.domain.SysUser;
import com.cbb.system.mapper.SysUserMapper;
import com.cbb.system.service.ISysUserService;
import com.cbb.system.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private TokenService tokenService;

    @Override
    public Result Login(String userAccount,String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserAccount, userAccount);
        queryWrapper.select(SysUser::getUserId,SysUser::getPassword);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        //失败说明这个用户是不存在的
        if (sysUser == null) {
            return Result.fail(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        //判断密码是否正确
        if(BCryptUtils.matchPassword(password,sysUser.getPassword())){
            return Result.ok(tokenService.createToken(sysUser.getUserId(), secret, UserIdentity.ADMIN.getValue()));
        }

        //下面是密码错误的情况
        return Result.fail(ResultCode.FAILED_LOGIN_FAILED);
    }
}

