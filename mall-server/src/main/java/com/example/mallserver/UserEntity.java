package com.example.mallserver;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private Integer userId;
    private String username;
    private String password; // 注意：实际应用中应使用加密密码
    private String email;
    private Date createdAt;
    private Date updatedAt;

// Getters and setters
}
