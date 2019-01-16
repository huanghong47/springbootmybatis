package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.service.BaseService;
import cn.hhfarcry.springbootmybatis.example.dao.RoleDao;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:21
 */
@Service
@Transactional
public class RoleService extends BaseService<RoleEntity> implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @PostConstruct
    protected void init() {
        super.baseDao = roleDao;
    }

    @Override
    public String insertRole(RoleEntity param) {
        super.buildUser(param);
        roleDao.insertOrUpdateByEntity(param);
        return "ok";
    }
}
