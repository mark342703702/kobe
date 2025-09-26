package com.example.kobe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类（适配新表结构）
 */
@Data
@TableName("sys_user") // 对应数据库表名
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID（主键）
     * 新表主键为id，与实体类字段一致
     */
    @TableId(type = IdType.AUTO) // 自增策略，匹配表中AUTO_INCREMENT
    private Long id;

    /** 登录账号（表中为username，非user_name） */
//    @NotBlank(message = "登录账号不能为空")
//    @Length(min = 1, max = 50, message = "登录账号长度不能超过50个字符")
    private String username; // 注意：表中字段是username（非下划线），实体类直接用username

    /** 密码 */
    //    @NotBlank(message = "密码不能为空")
    @JsonIgnore
    private String password;

    /** 用户昵称 */
//    @Length(max = 50, message = "用户昵称长度不能超过50个字符")
    private String nickname;

    /** 所属部门ID */
    private Long deptId;

    /** 状态（0正常，1禁用） */
//    @NotNull(message = "状态不能为空")
    private Integer status; // 表中是tinyint，实体类用Integer接收

    /** 删除标志（0存在，1删除） */
    private Integer delFlag; // 与表中tinyint对应


    // 超级管理员判断（约定id=1为超级管理员）
    public boolean isAdmin() {
        return this.id != null && 1L == this.id;
    }

    public static boolean isAdmin(Long id) {
        return id != null && 1L == id;
    }
}
