package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.service.BaseService;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.service.IUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Service
@Transactional
public class UserService  extends BaseService<UserEntity> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    @PostConstruct
    public void init(){
        super.baseDao = userDao;
    }

    @Override
    public String insertUser(UserEntity param) {
        super.buildUser(param);
        userDao.insertOrUpdateByEntity(param);
        return "ok";
    }

    @Override
    public List<UserEntity> getlist(Map<String, Object> param) {
        return userDao.selectByEntity(param);
    }

    @Override
    public Page<UserEntity> getPage(Map<String, Object> param) {
        return super.getPage(param);
    }
}
