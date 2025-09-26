package com.example.kobe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kobe.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    // 继承BaseMapper后，自动拥有CRUD方法（无需写XML）
}
