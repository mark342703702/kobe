package com.example.kobe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.kobe.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user") // 对应数据库表名
public class SysUser extends BaseEntity {
    /**
     * 用户ID（主键）
     * 新表主键为id，与实体类字段一致
     */
    @TableId(type = IdType.AUTO) // 自增策略，匹配表中AUTO_INCREMENT
    private Long id;

    /** 登录账号（表中为username，非user_name） */
    private String username; // 注意：表中字段是username（非下划线），实体类直接用username

    /** 密码 */
    private String password;

    /** 用户昵称 */
    private String nickname;

    /** 所属部门ID */
    private Long deptId;

    /** 状态（0正常，1禁用） */
    private Integer status; // 表中是tinyint，实体类用Integer接收

}
