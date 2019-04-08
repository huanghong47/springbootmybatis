package cn.hhfarcry.springbootmybatis.example.service.impl;


import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.dao.ResourceDao;
import cn.hhfarcry.springbootmybatis.example.dao.RoleDao;
import cn.hhfarcry.springbootmybatis.example.dao.RoleResourceDao;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.entity.RoleResourceEntity;
import cn.hhfarcry.springbootmybatis.example.service.IRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: emsog
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
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    @PostConstruct
    protected void init() {
        super.baseDao = roleDao;
    }

    @Override
    public String insertRole(RoleEntity param) {
        Map<String,Object>map = new HashMap<>();
        map.put("roleName",param.getRoleName());
        List<RoleEntity>existRolename = roleDao.selectByEntity(map);
        if(CollectionUtils.isNotEmpty(existRolename)){
            if((ParamUtils.isNotBlank(param.getId()) && !existRolename.get(0).getId().equals(param.getId()))
                    || (ParamUtils.isBlank(param.getId()))){
                return "角色名已存在";
            }
        }
        super.buildUser(param);
        roleDao.insertOrUpdateByEntity(param);
        return "ok";
    }

    @Override
    public String bindroleResources(String roleId, List<String> resourceIds) {
        RoleEntity existRole = roleDao.getOne(ParamUtils.strTIntger(roleId));
        if(ParamUtils.isBlank(existRole)){
            return "角色不存在";
        }

        //删除角色现有的资源
        roleResourceDao.deleteRoleResources(ParamUtils.strTIntger(roleId));
        //查询resourceIds中有效的id
        List<ResourceEntity>existRes = resourceDao.getResourcesByResourceIds(ParamUtils.strlisttointlist(resourceIds));
        List<RoleResourceEntity>params = new ArrayList<>();
        for (ResourceEntity resourceEntity : existRes) {
            RoleResourceEntity param = new RoleResourceEntity();
            param.setRoleId(ParamUtils.strTIntger(roleId));
            param.setResourceId(resourceEntity.getId());
            params.add(param);
        }
        if(CollectionUtils.isNotEmpty(params)){
            roleResourceDao.insertRoleResources(params);
        }
        return "ok";
    }

    @Override
    public List<RoleEntity> getRolesByUserId(String userId) {
        return roleDao.getRolesByUserId(ParamUtils.strTIntger(userId));
    }


}
