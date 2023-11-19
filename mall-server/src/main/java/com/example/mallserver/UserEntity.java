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
    @NotEmpty(message = "Username must not be empty")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    private String password; // 注意：实际应用中应使用加密密码
    @Email(message = "Email should be valid")
    private String email;
    private Date createdAt;
    private Date updatedAt;

// Getters and setters
}
