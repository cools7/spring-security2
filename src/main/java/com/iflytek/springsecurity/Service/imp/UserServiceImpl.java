package com.iflytek.springsecurity.Service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.iflytek.springsecurity.Service.UserService;
import com.iflytek.springsecurity.entity.Role;
import com.iflytek.springsecurity.entity.User;
import com.iflytek.springsecurity.entity.UserRole;
import com.iflytek.springsecurity.mapper.RoleMapper;
import com.iflytek.springsecurity.mapper.UserMapper;
import com.iflytek.springsecurity.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cool
 * @version V1.0
 * @className UserServiceImpl
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUserByName(String name) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.lambda().eq(User::getUserName, name);
        return userMapper.selectOne(queryWrapper);
    }


}
