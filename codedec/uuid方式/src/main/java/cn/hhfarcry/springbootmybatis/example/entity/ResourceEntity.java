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
public class ResourceEntity extends BaseEntity {

    //资源名称
    @Setter@Getter private String resourceName;

    //(0后端资源1前端资源)
    @Setter@Getter private Integer resourceType;

    //资源接口地址或菜单名称
    @Setter@Getter private String resourceUrl;
}
