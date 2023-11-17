package com.example.mallserver.mapper;

import com.example.mallserver.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {
    List<String> getAllUsers();

    @Select("SELECT * FROM users WHERE UserID = #{id}")
    UserEntity getUserById(@Param("id") long id);

    @Insert("INSERT INTO users(name, email, ...) VALUES(#{name}, #{email}, ...)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(UserEntity user);

    @Update("UPDATE users SET name = #{name}, email = #{email}, ... WHERE UserID = #{id}")
    int updateUser(@Param("id") long id, UserEntity user);

    @Delete("DELETE FROM users WHERE UserID = #{id}")
    int deleteUser(@Param("id") long id);
}