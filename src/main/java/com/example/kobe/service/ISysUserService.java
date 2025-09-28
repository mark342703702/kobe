package com.example.kobe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kobe.entity.SysUser;

public interface ISysUserService extends IService<SysUser> {
    // 继承IService，拥有基础CRUD和分页等方法

    IPage<SysUser> list(Integer pageNum, Integer pageSize, String username);
}
