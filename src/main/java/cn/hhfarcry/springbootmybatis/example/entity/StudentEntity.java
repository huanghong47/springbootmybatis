package cn.hhfarcry.springbootmybatis.example.entity;

import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-02 15:31
 */
@NoArgsConstructor
@Data
public class StudentEntity extends BaseEntity {
    private String name;
    private String phone;
}
