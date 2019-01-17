package cn.hhfarcry.springbootmybatis.example.entity;


import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserAddGroup;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserLoginGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:15
 */
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @NotNull(message = "用户名不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String userName;

    @NotNull(message = "密码不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String password;
}
