package cn.hhfarcry.springbootmybatis.common.loginstorage.mymanager;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 16:46
 */
@Repository
@Mapper
public interface LogInfoDao {
    int insertLogInfo(LogInfoEntity params);
}
