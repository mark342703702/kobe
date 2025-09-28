package com.example.kobe.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.example.kobe.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user") // 对应数据库表名
public class SysUser extends BaseEntity {
    /** 登录账号（表中为username，非user_name） */
    private String username; // 注意：表中字段是username（非下划线），实体类直接用username

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 用户昵称 */
    private String nickname;

    /** 所属部门ID */
    private Long deptId;

    /** 状态（0正常，1禁用） */
    private Integer status; // 表中是tinyint，实体类用Integer接收

}
