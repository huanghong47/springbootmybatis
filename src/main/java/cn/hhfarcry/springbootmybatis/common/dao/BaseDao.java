package cn.hhfarcry.springbootmybatis.common.dao;

import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 11:02
 */
public interface BaseDao <E extends BaseEntity>{
    /**
     * 查询（动态分页）
     * @param param
     * @return
     */
    List<E> selectByEntity(Map<String, Object> param);

    /**
     * 新增或修改
     * @param entity
     */
    int insertOrUpdateByEntity(E entity);
}
