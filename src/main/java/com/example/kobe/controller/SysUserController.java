package com.example.kobe.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kobe.dto.UserDto;
import com.example.kobe.entity.SysUser;
import com.example.kobe.exception.BusinessException;
import com.example.kobe.service.ISysUserService;
import com.example.kobe.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/sysUser")
@Tag(name="用户管理", description = "系统用户的查询、新增、编辑、删除接口")
public class SysUserController {

    // 1. 声明日志对象（参数为当前类的Class，便于定位日志来源）
    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

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
    public Result<SysUser> getUserInfo(@Min(value = 1, message = "ID必须大于0") @RequestParam Long userId) {

        log.info("开始查询用户详情，查询条件：userId: {}", userId);

        SysUser sysUser = sysUserService.getById(userId);

        if (sysUser == null) {
            throw new BusinessException("用户不存在userId:" + userId);
        }

        return Result.success(sysUser);
    }

    @Operation(summary = "新增用户", description="新增系统用户")
    @PostMapping("/addSysUser")
    public Result<Void> addSysUser(@Valid @RequestBody UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(userDto, sysUser);
        sysUser.setPassword("123456");
        sysUser.setStatus(0);
        sysUserService.save(sysUser);
        return Result.success();
    }

    @Operation(summary = "修改用户", description="修改系统用户")
    @PostMapping("/updateSysUser")
    public Result<Void> updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.success();
    }

    @Operation(summary = "删除用户", description="删除系统用户")
    @DeleteMapping("/deleteSysUser")
    public Result<Void> deleteSysUser(Long userId) {
        sysUserService.removeById(userId);
        return Result.success();
    }

    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<IPage<SysUser>> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String username) {
        IPage<SysUser> page = sysUserService.list(pageNum, pageSize, username);
        return Result.success(page);
    }

}
