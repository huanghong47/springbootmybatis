package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.example.dao.SchoolDao;
import cn.hhfarcry.springbootmybatis.example.entity.SchoolEntity;
import cn.hhfarcry.springbootmybatis.example.service.ISchoolService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-04-02 16:04
 */
@Service
@Transactional
public class SchoolService extends BaseService<SchoolEntity> implements ISchoolService {


    @Autowired
    private SchoolDao schoolDao;

    @Override
    public String add(SchoolEntity param) {
        super.buildUser(param);
        schoolDao.insertOrUpdateByEntity(param);
        return "ok";
    }

    @Override
    public Page<SchoolEntity> getpage(Map<String, Object> param) {
        return super.getPage(param);
    }
}
