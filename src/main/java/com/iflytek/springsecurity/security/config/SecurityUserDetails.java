package com.iflytek.springsecurity.security.config;

import com.google.gson.Gson;
import com.iflytek.springsecurity.entity.Role;
import com.iflytek.springsecurity.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:springsecurity用户权限包装了
 * @Author: zule
 * @Date: 2019/5/6
 */
@Slf4j
public class SecurityUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(User user) {
        if(user!=null) {
            this.setId(user.getId());
            this.setUserName(user.getUserName());
            this.setPassword(user.getPassword());
            this.setEnable(user.getEnable());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roles = this.getRoles();
        if(roles!=null&&roles.size()>0){
            for (Role role : roles) {
                authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleKey()));
            }
        }
        log.info("当前登录用户所拥有的权限:" + new Gson().toJson(authorityList));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}