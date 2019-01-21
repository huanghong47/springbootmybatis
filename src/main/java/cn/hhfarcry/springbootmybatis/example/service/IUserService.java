package cn.hhfarcry.springbootmybatis.example.service;

import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
public interface IUserService {
    String insertUser(UserEntity param);
    Page<UserEntity> getPage(Map<String,Object> param);
    String bindUserRoles(String userId,List<String>roleIds);
    String loginUser(UserEntity param);

}
