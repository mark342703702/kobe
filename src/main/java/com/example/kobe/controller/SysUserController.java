package com.example.kobe.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.kobe.entity.SysUser;
import com.example.kobe.service.ISysUserService;
import com.example.kobe.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

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
}
