package com.cbb.system.controller;

import com.cbb.common.core.domain.Result;
import com.cbb.system.domain.LoginDTO;
import com.cbb.system.domain.SysUserSaveDTO;
import com.cbb.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysUser")
@Tag(name = "管理员用户接口")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Operation(summary = "登录",description = "根据用户Account和密码进行登录")
    @ApiResponse(responseCode = "1000",description = "登录成功")
    @ApiResponse(responseCode = "3102",description = "用户不存在")
    @ApiResponse(responseCode = "3103",description = "用户名或密码错误")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO){
        return sysUserService.Login(loginDTO.getUserAccount(),loginDTO.getPassword());
    }

    @Operation(summary = "新增管理员用户",description = "根据提供的信息新增管理员用户")
    @ApiResponse(responseCode = "1000",description = "新增成功")
    @ApiResponse(responseCode = "3101",description = "用户已存在")
    @ApiResponse(responseCode = "2000",description = "服务器繁忙清稍后重试")
    @PostMapping("/add")
    public Result add(@RequestBody SysUserSaveDTO sysUserSaveDTO){
        return null;
    }

    @DeleteMapping("/{userid}")
    @Operation(summary = "删除管理员用户",description = "根据提供的用户ID删除管理员用户")
    @Parameters(value = {@Parameter(name="userid",in = ParameterIn.PATH,required = true,description = "用户ID")})
    @ApiResponse(responseCode = "1000",description = "删除成功")
    @ApiResponse(responseCode = "3101",description = "用户不存在")
    @ApiResponse(responseCode = "2000",description = "服务器繁忙清稍后重试")
    public Result delete(@PathVariable Long userid){
        return null;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "查询管理员用户详情",description = "根据提供的用户ID查询管理员用户详情")
    @Parameters(value = {
            @Parameter(name="userId",in = ParameterIn.QUERY,required = true,description = "用户ID"),
            @Parameter(name="sex",in=ParameterIn.QUERY,description="用户性别")
    })
    @ApiResponse(responseCode = "1000",description = "查询成功")
    @ApiResponse(responseCode = "3101",description = "用户不存在")
    @ApiResponse(responseCode = "2000",description = "服务器繁忙清稍后重试")
    public Result detail(Long userId,@RequestParam(required = false) String sex){
        return null;
    }
}
