package com.example.kobe.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kobe.entity.SysUser;
import com.example.kobe.mapper.SysUserMapper;
import com.example.kobe.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    // 无需写额外代码，继承ServiceImpl后自动实现IService的方法

    @Override
    public IPage<SysUser> list(Integer pageNum, Integer pageSize, String username) {
        pageNum = (pageNum == null || pageNum < 1) ? 1 : pageNum;
        pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;

        Page<SysUser> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.hasText(username)) {
            queryWrapper.like(SysUser::getUsername, username);
        }

        // 按创建时间降序（最新的在前）
        queryWrapper.orderByDesc(SysUser::getCreateTime);

        // 3. 执行分页查询（MyBatis-Plus自动处理分页）

        return baseMapper.selectPage(page, queryWrapper);
    }
}
