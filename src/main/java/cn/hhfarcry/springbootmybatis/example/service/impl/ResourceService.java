package cn.hhfarcry.springbootmybatis.example.service.impl;

import cn.hhfarcry.springbootmybatis.common.service.BaseService;
import cn.hhfarcry.springbootmybatis.example.dao.ResourceDao;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import cn.hhfarcry.springbootmybatis.example.service.IResourceService;
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
public class ResourceService extends BaseService<ResourceEntity> implements IResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    @PostConstruct
    protected void init() {
        super.baseDao = resourceDao;
    }

    @Override
    public String insertResource(ResourceEntity param) {
        super.buildUser(param);
        resourceDao.insertOrUpdateByEntity(param);
        return "ok";
    }
}
