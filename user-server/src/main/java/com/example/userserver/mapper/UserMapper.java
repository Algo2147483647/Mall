package com.example.userserver.mapper;

import com.example.userserver.UserEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<UserEntity> getAllUsers();

    @Select("SELECT * FROM users WHERE UserID = #{id}")
    UserEntity getUserById(@Param("id") long id);

    @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
    boolean existsByEmail(String email);

    void createUser(UserEntity user);

    int updateUser(UserEntity user);

    @Delete("DELETE FROM users WHERE UserID = #{id}")
    int deleteUser(@Param("id") long id);
}