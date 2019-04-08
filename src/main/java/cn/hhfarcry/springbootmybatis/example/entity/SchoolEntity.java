package cn.hhfarcry.springbootmybatis.example.entity;

import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-02 15:30
 */
@NoArgsConstructor
@Data
public class SchoolEntity extends BaseEntity {
    private String name;
    private List<StudentEntity>students;
}
