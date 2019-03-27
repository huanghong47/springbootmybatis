package cn.hhfarcry.springbootmybatis.example.service.impl;


import cn.hhfarcry.springbootmybatis.common.base.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.dao.FBResourceDao;
import cn.hhfarcry.springbootmybatis.example.dao.ResourceDao;
import cn.hhfarcry.springbootmybatis.example.dao.RoleResourceDao;
import cn.hhfarcry.springbootmybatis.example.entity.FBResourceEntity;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import cn.hhfarcry.springbootmybatis.example.entity.RoleResourceEntity;
import cn.hhfarcry.springbootmybatis.example.service.IResourceService;
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
public class ResourceService extends BaseService<ResourceEntity> implements IResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private FBResourceDao fbResourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    @PostConstruct
    protected void init() {
        super.baseDao = resourceDao;
    }

    @Override
    public String insertResource(ResourceEntity param) {
        Map<String,Object>map = new HashMap<>();
        map.put("resourceUrl",param.getResourceUrl());
        List<ResourceEntity>resources = resourceDao.selectByEntity(map);

        if(CollectionUtils.isNotEmpty(resources)){
            if((ParamUtils.isNotBlank(param.getId()) && !param.getId().equals(resources.get(0).getId()))
                    || (ParamUtils.isBlank(param.getId()))){
                return "资源地址已存在";
            }
        }
        super.buildUser(param);
        resourceDao.insertOrUpdateByEntity(param);
        return "ok";
    }

    @Override
    public List<ResourceEntity> getResourcesByRoleId(String roleId) {
        return resourceDao.getResourcesByRoleId(ParamUtils.strTIntger(roleId));
    }

    @Override
    public List<ResourceEntity> getResourcesByUserId(String userId) {
        return resourceDao.getResourcesByUserId(ParamUtils.strTIntger(userId));
    }

    @Override
    public String bindfbresources(Integer frontId, List<Integer> backIds) {
//        //查出现有的绑定关系
//        Map<String,Object>params = new HashMap<>();
//        params.put("frontId",frontId);
//        List<FBResourceEntity> existfbr = fbResourceDao.selectByEntity(params);
        //查出角色资源表中绑定了以上前端资源的角色
        List<Integer> existroles = roleResourceDao.getlistByresourceid(frontId);

        //删除现有的前后端绑定关系
        fbResourceDao.deleteByfrontid(frontId);
        //删除现有的角色资源绑定关系
        roleResourceDao.deleteInbatchRoleResources(existroles);

        //绑定新的前后端资源
        List<FBResourceEntity> nowfbrs = new ArrayList<>();
        for (Integer backId : backIds) {
            FBResourceEntity fbResourceEntity = new FBResourceEntity();
            fbResourceEntity.setFrontId(frontId);
            fbResourceEntity.setBackId(backId);
            nowfbrs.add(fbResourceEntity);
        }
        fbResourceDao.insertByEntityBatch(nowfbrs);

        //绑定新的角色资源
        List<RoleResourceEntity>nowrrs = new ArrayList<>();
        for (Integer existrole : existroles) {
            RoleResourceEntity roleResourceEntity = new RoleResourceEntity();
            roleResourceEntity.setResourceId(frontId);
            roleResourceEntity.setRoleId(existrole);
            nowrrs.add(roleResourceEntity);
            for (Integer backId : backIds) {
                RoleResourceEntity roleResourceEntity2 = new RoleResourceEntity();
                roleResourceEntity2.setRoleId(existrole);
                roleResourceEntity2.setResourceId(backId);
                nowrrs.add(roleResourceEntity2);
            }
        }
        roleResourceDao.insertRoleResources(nowrrs);
        return "ok";
    }
}
