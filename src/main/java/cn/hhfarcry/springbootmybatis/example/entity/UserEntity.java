package cn.hhfarcry.springbootmybatis.example.entity;


import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:15
 */
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    /**
     * 用户名
     */
    //@NotNull(message = "用户名不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String userName;

    /**
     * 密码
     */
    //@NotNull(message = "密码不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String password;

    @Setter@Getter
    private String phone;

    /**
     * 用户角色1客户2普管3超管
     */
    @Setter@Getter
    private List<RoleEntity> userRole;
}
