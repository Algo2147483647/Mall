package com.example.userserver;

import com.example.userserver.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserEntity> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public UserEntity getUserById(long id) {
        return Optional.ofNullable(userMapper.getUserById(id))
                       .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found."));
    }

    @Transactional
    public UserEntity createUser(UserEntity user) {
        if (userMapper.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("该邮箱已被注册。");
        }

        Date now = Date.from(Instant.now());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userMapper.createUser(user);
        return user;
    }

    @Transactional
    public void updateUser(long id, UserEntity user) {
        UserEntity currentUser = userMapper.getUserById(id);

        if (currentUser == null) {
            log.info("error: User with id {} not found.", id);
            throw new NoSuchElementException("User with id " + id + " not found.");
        }

        Field[] fields = user.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // 使得私有字段也可以被访问
            try {
                Object value = field.get(user);
                if (value != null) {
                    Field userField = UserEntity.class.getDeclaredField(field.getName());
                    userField.setAccessible(true);
                    userField.set(currentUser, value);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                // 处理异常：可能是字段不可访问或User类中不存在对应字段
                e.printStackTrace();
            }
        }

        Date now = Date.from(Instant.now());
        currentUser.setUpdatedAt(now);

        if(userMapper.updateUser(currentUser) == 0) {
            log.info("error: update user with id: {}", id);
            throw new NoSuchElementException("User with id " + id + " not found.");
        }
    }

    @CacheEvict(value = "usersCache", key = "'allUsers'")
    public void deleteUser(long id) {
        if(userMapper.deleteUser(id) == 0) {
            log.info("error: Deleting user with id: {}", id);
            throw new NoSuchElementException("User with id " + id + " not found.");
        }
    }
}