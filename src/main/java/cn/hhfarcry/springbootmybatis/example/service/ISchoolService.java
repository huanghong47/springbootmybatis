package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.SchoolEntity;
import cn.hhfarcry.springbootmybatis.example.entity.StudentEntity;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-02 16:03
 */
public interface ISchoolService {
    String add(SchoolEntity param);
    Page<SchoolEntity> getpage(Map<String,Object> param);
}
