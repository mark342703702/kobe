package com.example.kobe.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyBatisPlusFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject){
        System.out.println("开始插入数据");
        // 1. 填充创建时间和更新时间（插入时两者一致）
        // strictInsertFill：严格模式，只有字段为null时才填充（非用户手动填充）
        this.strictInsertFill(
                metaObject,
                "createTime",  // 实体类中的字段名（如BaseEntity的createTime）
                LocalDateTime.class,  // 字段类型
                LocalDateTime.now()   // 填充值（当前时间）
        );

        this.strictInsertFill(
                metaObject,
                "updateTime",
                LocalDateTime.class,
                LocalDateTime.now()
        );

        // 2. 填充创建人ID和更新人ID（实际项目需从登录上下文获取）
        // 示例：假设当前登录用户ID为1（实际应替换为真实用户ID，如从SecurityContext获取）
        this.strictInsertFill(metaObject, "createBy", Long.class, 1L);
        this.strictInsertFill(metaObject, "updateBy", Long.class, 1L);

        // 3. 填充默认删除标志（0=未删除，与BaseEntity的delFlag对应）
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject){
        System.out.println("开始更新数据");
        this.strictUpdateFill(
                metaObject,
                "updateTime",
                LocalDateTime.class,
                LocalDateTime.now()
        );

        // 2. 填充更新人ID（更新时为当前登录用户）
        this.strictUpdateFill(metaObject, "updateBy", Long.class, 2L);
    }
}
