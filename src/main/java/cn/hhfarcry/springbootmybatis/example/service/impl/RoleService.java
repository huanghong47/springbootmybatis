package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.dao.RoleDao;
import cn.hhfarcry.springbootmybatis.example.dao.RoleResourceDao;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.entity.RoleResourceEntity;
import cn.hhfarcry.springbootmybatis.example.service.IRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleResourceDao roleResourceDao;

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

    @Override
    public String bindroleResources(String roleId, List<String> resourceIds) {
        roleResourceDao.deleteRoleResources(ParamUtils.strTIntger(roleId));
        List<RoleResourceEntity>params = new ArrayList<>();
        for (String resourceId : resourceIds) {
            RoleResourceEntity param = new RoleResourceEntity();
            param.setRoleId(ParamUtils.strTIntger(roleId));
            param.setResourceId(ParamUtils.strTIntger(resourceId));
            params.add(param);
        }
        if(CollectionUtils.isNotEmpty(params)){
            roleResourceDao.insertRoleResources(params);
        }
        return "ok";
    }

    @Override
    public List<RoleEntity> getRolesByUserId(Integer userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
