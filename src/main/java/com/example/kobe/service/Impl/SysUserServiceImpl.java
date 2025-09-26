package com.example.kobe.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kobe.entity.SysUser;
import com.example.kobe.mapper.SysUserMapper;
import com.example.kobe.service.ISysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    // 无需写额外代码，继承ServiceImpl后自动实现IService的方法
}
