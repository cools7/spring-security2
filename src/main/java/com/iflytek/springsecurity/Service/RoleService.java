package com.iflytek.springsecurity.Service;


import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * 名称对应角色
     * @param name
     * @return
     */
    List<Role> findUserRolesByName(String name);

    /**
     * 资源对应角色
     * @param resources
     * @return
     */
    List<Role> findUserRolesByResources(Resources resources) ;
}
