package com.example.kobe.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.kobe.entity.SysUser;
import com.example.kobe.exception.BusinessException;
import com.example.kobe.mapper.SysUserMapper;
import com.example.kobe.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper sysUserMapper;


    public String login(String username, String password) {
        //1.查询用户 （用户名+未删除匹配）
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username)
                .eq("del_flag", 0);

        SysUser user = sysUserMapper.selectOne(queryWrapper);

        if(user == null) {
            throw new BusinessException("用户名或密码错误");
        }




        return "登录成功";
    }
}
