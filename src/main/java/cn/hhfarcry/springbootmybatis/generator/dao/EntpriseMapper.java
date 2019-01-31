package cn.hhfarcry.springbootmybatis.generator.dao;

import cn.hhfarcry.springbootmybatis.generator.entity.Entprise;
import cn.hhfarcry.springbootmybatis.generator.entity.EntpriseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EntpriseMapper {
    long countByExample(EntpriseExample example);

    int deleteByExample(EntpriseExample example);

    int insert(Entprise record);

    int insertSelective(Entprise record);

    List<Entprise> selectByExample(EntpriseExample example);

    int updateByExampleSelective(@Param("record") Entprise record, @Param("example") EntpriseExample example);

    int updateByExample(@Param("record") Entprise record, @Param("example") EntpriseExample example);
}