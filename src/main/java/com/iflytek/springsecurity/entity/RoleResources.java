package com.iflytek.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *   Entity  实体
 * </p>
 * @author cools
 * @creatDate 2019-05-22
 * @description Code Is Poetry.
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_resources")
public class RoleResources implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer resourcesId;


}