package com.example.kobe.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.kobe.entity.SysUser;
import com.example.kobe.service.ISysUserService;
import com.example.kobe.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
@Tag(name="用户管理", description = "系统用户的查询、新增、编辑、删除接口")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    @Operation(summary = "用户列表", description="支持按用户名、部门ID、昵称筛选，自动过滤已删除用户")
    @GetMapping("/list")
    public Result<List<SysUser>> list(SysUser user) {
        // 构建查询条件（自动拼接非空字段作为查询条件）
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(user);

        // 按创建时间倒序（最新的用户排在前面）
        queryWrapper.orderByDesc("create_time");

        // 调用Service查询列表（MyBatis-Plus会自动过滤del_flag=1的已删除数据）
        List<SysUser> list = sysUserService.list(queryWrapper);
        return Result.success(list);

    }


    @Operation(summary = "用户详情", description="根据用户ID查询用户详情")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(Long userId) {
        System.out.println("userId:" + userId);

        SysUser sysUser = sysUserService.getById(userId);

        if (sysUser == null) {
            return Result.error("用户不存在");
        }

        return Result.success(sysUser);
    }

    @Operation(summary = "新增用户", description="新增系统用户")
    @GetMapping("/addSysUser")
    public Result<Void> addSysUser(SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.success();
    }

    @Operation(summary = "修改用户", description="修改系统用户")
    @GetMapping("/updateSysUser")
    public Result<Void> updateSysUser(SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.success();
    }

    @Operation(summary = "删除用户", description="删除系统用户")
    @GetMapping("/deleteSysUser")
    public Result<Void> deleteSysUser(Long userId) {
        sysUserService.removeById(userId);
        return Result.success();
    }

}
