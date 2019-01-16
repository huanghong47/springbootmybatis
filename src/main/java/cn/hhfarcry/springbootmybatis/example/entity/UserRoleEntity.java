package cn.hhfarcry.springbootmybatis.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:18
 */
@NoArgsConstructor
public class UserRoleEntity {

    @Setter@Getter
    private Integer id;

    @Setter@Getter
    private Integer userId;

    @Setter@Getter
    private Integer roleId;

}
