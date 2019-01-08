package cn.hhfarcry.springbootmybatis.entity;


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
public class UserEntity {

    @Setter@Getter
    private Integer id;

    @Setter@Getter
    private String name;

}
