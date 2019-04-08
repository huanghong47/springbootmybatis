package cn.hhfarcry.springbootmybatis.example.dao;

import cn.hhfarcry.springbootmybatis.example.entity.FBResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: equipmentMaintenanceSystemOfGuotaiBackside
 * @description: dao层
 * @author: huanghong
 * @date: 2019-03-14 16:20
 */
@Repository
@Mapper
public interface FBResourceDao {
    /**
     * 查询（动态分页）
     * @param param
     * @return
     */
    List<FBResourceEntity> selectByEntity(Map<String, Object> param);

    /**
     * 新增或修改
     * @param entity
     */
    int insertOrUpdateByEntity(FBResourceEntity entity);

    /**
     * 新增
     * @param entity
     */
    int insertByEntity(FBResourceEntity entity);

    /**
     * 修改
     * @param entity
     */
    int updateByEntity(FBResourceEntity entity);

    /**
     * 批量新增
     * @param entitys
     * @return
     */
    int insertByEntityBatch(List<FBResourceEntity> entitys);

    int deleteByfrontid(Integer frontid);
}
