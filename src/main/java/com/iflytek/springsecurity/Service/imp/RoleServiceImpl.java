package com.iflytek.springsecurity.Service.imp;

import com.iflytek.springsecurity.Service.RoleService;
import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.entity.Role;
import com.iflytek.springsecurity.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cool
 * @version V1.0
 * @className RoleServiceImpl
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Role> findUserRolesByName(String name) {
        return roleMapper.findUserRolesByName(name);
    }

    @Override
    public List<Role> findUserRolesByResources(Resources resources) {
        return roleMapper.findUserRolesByResources(resources);
    }
}
