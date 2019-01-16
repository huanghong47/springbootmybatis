package cn.hhfarcry.springbootmybatis.example.entity;


import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:15
 */
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Setter@Getter
    private String userName;

    @Setter@Getter
    private String password;
}
