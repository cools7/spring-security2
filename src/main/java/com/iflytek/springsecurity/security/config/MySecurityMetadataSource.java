package com.iflytek.springsecurity.security.config;

import com.iflytek.springsecurity.Service.ResourcesService;
import com.iflytek.springsecurity.Service.RoleService;
import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.entity.Role;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @author cool
 * @version V1.0
 * @className MySecurityMetadataSource
 * @description 用来加载资源与权限的全部对应关系的，并提供一个通过资源获取所有权限的方法。
 * @createDate 2019年05月21日
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private RoleService roleService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * @PostConstruct是Java EE 5引入的注解，
     * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
     * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
     *
     * 加载所有资源与权限的关系
     */
    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Resources> list = resourcesService.queryAll();
            for (Resources resources : list) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                // 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头

              List<Role> roleList=roleService.findUserRolesByResources(resources);
                for (Role role:
                        roleList) {
                    logger.info(resources.getResUrl()+"资源对应的角色:" + "ROLE_" + role.getRoleKey());
                    ConfigAttribute configAttribute = new SecurityConfig("ROLE_" +role.getRoleKey());
                    configAttributes.add(configAttribute);
                    resourceMap.put(resources.getResUrl(), configAttributes);
                }

            }
        }
    }

    /**
     * 返回所请求资源所需要的权限
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        logger.info("请求url  is " + requestUrl);
        if (resourceMap == null) {
            loadResourceDefine();
        }
        if (requestUrl.indexOf("?") > -1) {
            requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
        }
        Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
        return configAttributes;
    }


}