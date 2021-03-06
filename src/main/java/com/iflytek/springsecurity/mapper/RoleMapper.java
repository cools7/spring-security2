package com.iflytek.springsecurity.mapper;

import com.iflytek.springsecurity.entity.Resources;
import com.iflytek.springsecurity.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @author cools
 * @creatDate 2019-05-22
 * @description Code Is Poetry.
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findUserRolesByName(@Param("name") String name);

    List<Role> findUserRolesByResources(@Param("resources") Resources resources);
}
