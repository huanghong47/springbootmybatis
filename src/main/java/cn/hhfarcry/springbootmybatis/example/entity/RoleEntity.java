package cn.hhfarcry.springbootmybatis.example.entity;

import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:17
 */
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @Setter @Getter private String roleName;
}
