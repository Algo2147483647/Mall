package com.example.mallserver.mapper;

import com.example.mallserver.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    List<String> getAllUsers();
}