package com.example.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.dev33.satoken.SaManager;

@Configuration
public class SaTokenConfig {
    @Bean
    public SaTokenConfig getSaTokenConfig() {
        // 配置全局过期时间（单位：秒）
        SaManager.getConfig().setIdTokenTimeout(7200);
        // 配置全局签名密钥
        SaManager.getConfig().setJwtSecretKey("your-secret-key");
        return new SaTokenConfig();
    }
}
