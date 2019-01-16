package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:21
 */
public interface IRoleService {
    String insertRole(RoleEntity param);
    Page<RoleEntity> getPage(Map<String,Object> param);
}
