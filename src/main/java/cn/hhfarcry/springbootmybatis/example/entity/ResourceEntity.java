package cn.hhfarcry.springbootmybatis.example.entity;

import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-15 14:17
 */
@NoArgsConstructor
public class ResourceEntity extends BaseEntity {

    @Setter@Getter private String resourceName;

    //(0后端资源1前端资源)
    @Setter@Getter private Integer resourceType;

    //资源地址或菜单
    @Setter@Getter private String resourceUrl;
}
