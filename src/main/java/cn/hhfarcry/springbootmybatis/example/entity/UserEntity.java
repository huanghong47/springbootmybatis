package cn.hhfarcry.springbootmybatis.example.entity;


import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserAddGroup;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserLoginGroup;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value="用户名",name="userName",example="xxx")
    @NotNull(message = "用户名不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String userName;

    @ApiModelProperty(value="密码",name="password",example="xxx")
    @NotNull(message = "密码不能为空", groups = {UserAddGroup.class, UserLoginGroup.class})
    @Setter@Getter
    private String password;
}
