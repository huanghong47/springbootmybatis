package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.example.dao.StudentDao;
import cn.hhfarcry.springbootmybatis.example.entity.StudentEntity;
import cn.hhfarcry.springbootmybatis.example.service.IStudentService;
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
public class StudentService extends BaseService<StudentEntity> implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public String add(StudentEntity param) {
        super.buildUser(param);
        studentDao.insertOrUpdateByEntity(param);
        return "ok";
    }

    @Override
    public Page<StudentEntity> getpage(Map<String, Object> param) {
        return super.getPage(param);
    }
}
