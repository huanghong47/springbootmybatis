package cn.hhfarcry.springbootmybatis.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:19
 */
@NoArgsConstructor
public class RoleResourceEntity {

    @Setter@Getter
    private Integer id;

    @Setter@Getter
    private Integer roleId;

    @Setter@Getter
    private Integer resourceId;

}
