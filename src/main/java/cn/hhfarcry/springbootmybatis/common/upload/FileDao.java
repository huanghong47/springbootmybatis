package cn.hhfarcry.springbootmybatis.common.upload;

import cn.hhfarcry.springbootmybatis.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-21 14:53
 */
@Repository
@Mapper
public interface FileDao extends BaseDao<FileEntity> {
    List<String>getRelation(Integer sonId);

}