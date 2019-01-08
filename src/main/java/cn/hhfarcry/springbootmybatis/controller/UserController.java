package cn.hhfarcry.springbootmybatis.controller;

import cn.hhfarcry.springbootmybatis.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-08 13:16
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/save" ,method = {RequestMethod.GET,RequestMethod.POST})
    public int save(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Map<String,String> param = new HashMap<>();
        param.put("name",name);
        return userService.insertUser(param);
    }

    @RequestMapping(value = "/getlist" ,method = {RequestMethod.GET,RequestMethod.POST})
    public List<UserEntity> getList(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        Map<String,String> param = new HashMap<>();
        param.put("name",name);
        return userService.getlist(param);
    }

}
