package com.example.kobe.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "认证管理", description = "认证管理接口")
@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/login")
    @Operation(summary = "登录接口")
    public String login(String username, String password) {
        System.out.println("用户名：" + username + " 密码：" + password);
        if("admin".equals(username) && "admin".equals(password)){
            StpUtil.login(123);
            return "登录成功";
        }
        return "登录失败";
    }


    @GetMapping("/isLogin")
    @Operation(summary = "查询登录状态")
    public String isLogin() {
        return StpUtil.isLogin() ? "已登录" : "未登录";
    }

}
