package com.example.mallserver;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserEntity {
    private Integer userId;
    private String username;
    private String password; // 注意：实际应用中应使用加密密码
    private String email;
    private Date createdAt;
    private Date updatedAt;

// Getters and setters
}
