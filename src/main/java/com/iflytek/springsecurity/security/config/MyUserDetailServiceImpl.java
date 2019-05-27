package com.iflytek.springsecurity.security.config;

import com.google.gson.Gson;
import com.iflytek.springsecurity.Service.ResourcesService;
import com.iflytek.springsecurity.Service.RoleService;
import com.iflytek.springsecurity.Service.UserService;
import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.entity.Role;
import com.iflytek.springsecurity.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sun.nio.cs.FastCharsetProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author cool
 * @version V1.0
 * @className MyUserDetailServiceImpl
 * @description 就是在登录时的认证操作交给Spring Security.在此处需要提供给当前登录用户所拥有的权限。即根据用户名查询上面t_resources表中的resKey
 *  * 拼凑成”ROLE_XXX“ 这样形式的字符串所组成的list ，交给spirngSecurity。
 * @createDate 2019年05月21日
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName + " not exist!");
        }
        List<Role>  roleList=roleService.findUserRolesByName(userName);
        user.setRoles(roleList);
        return new SecurityUserDetails(user);
    }

}
