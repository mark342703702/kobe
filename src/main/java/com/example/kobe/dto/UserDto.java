package com.example.kobe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDto {
    @Schema(description = "主键ID",  example = "1")
    private Long id;
    @Schema(description = "登录账号",  example = "admin")
    private String username;
    @Schema(description = "用户昵称",  example = "管理员")
    private String nickname;
    @Schema(description = "所属部门ID",  example = "1")
    private String deptId;
    @Schema(description = "状态（0正常，1禁用）",  example = "0")
    private String status;
}
