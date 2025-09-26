package com.example.kobe.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 1. 文档基本信息（标题、版本、描述）
                .info(new Info()
                        .title("Kobe 项目接口文档") // 文档标题（如你的项目名）
                        .version("1.0.0") // 接口版本
                        .description("本文档包含系统用户、部门等核心模块的接口，支持在线调试") // 文档描述
                        // 2. 作者信息
                        .contact(new Contact()
                                .name("开发者名称") // 你的名字/团队名
                                .email("your-email@example.com") // 联系方式
                                .url("https://github.com/your-repo") // 项目地址（可选）
                        )
                        // 3. 许可证信息（可选）
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}
