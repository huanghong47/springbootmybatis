package cn.hhfarcry.springbootmybatis.common.base.dao;

import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;

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

    /**
     * 新增
     * @param entity
     */
    int insertByEntity(E entity);

    /**
     * 修改
     * @param entity
     */
    int updateByEntity(E entity);

    /**
     * 批量新增
     * @param entitys
     * @return
     */
    int insertByEntityBatch(List<E> entitys);


    /**
     * 单条查询
     * @param id
     * @return
     */
    E getOne(Integer id);

    /**
     * 批量删除(软删)
     * @param ids
     */
    void softDeleteBatch(List<Integer> ids);

    /**
     * 批量删除(硬删)
     * @param ids
     */
    void hardDeleteBatch(List<Integer> ids);
}
