package cn.hhfarcry.springbootmybatis.example.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: equipmentMaintenanceSystemOfGuotaiBackside
 * @description: 前后端资源关系表
 * @author: huanghong
 * @date: 2019-03-14 14:25
 */
public class FBResourceEntity {
    /**
     * 主键id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 前端资源id
     */
    @Setter
    @Getter
    private Integer frontId;


    /**
     * 后端资源id
     */
    @Setter
    @Getter
    private Integer backId;

}
