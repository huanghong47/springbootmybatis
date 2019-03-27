package cn.hhfarcry.springbootmybatis.example.service.impl;


import cn.hhfarcry.springbootmybatis.common.security.utils.AesCipherUtil;
import cn.hhfarcry.springbootmybatis.example.dao.UserDao;
import cn.hhfarcry.springbootmybatis.example.dao.UserRoleDao;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.entity.UserRoleEntity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: equipmentMaintenanceSystemOfGuotaiBackside
 * @description: 启动时运行
 * @author: huanghong
 * @date: 2019-03-20 11:05
 */
@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //创建初始用户admin，密码123456
        List<UserEntity>users = userDao.selectByEntity(new HashMap<>());
        if(CollectionUtils.isEmpty(users)){
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("admin");
            userEntity.setPassword(AesCipherUtil.enCrypto("123456"));
            userEntity.setIsDeleted(0);
            userEntity.setCreateTime(new Date());
            userEntity.setCreateUserId(0);
            userEntity.setComment("初始用户");
            userDao.insertByEntity(userEntity);

            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(3);
            userRoleDao.insertUserRole(userRoleEntity);
        }
    }
}
