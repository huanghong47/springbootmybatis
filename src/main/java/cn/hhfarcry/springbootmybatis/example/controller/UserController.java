package cn.hhfarcry.springbootmybatis.example.controller;

import cn.hhfarcry.springbootmybatis.common.controller.BaseController;
import cn.hhfarcry.springbootmybatis.common.utils.EntityUtils;
import cn.hhfarcry.springbootmybatis.common.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.vo.ResponseVO;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserAddGroup;
import cn.hhfarcry.springbootmybatis.example.entity.valid.UserLoginGroup;
import cn.hhfarcry.springbootmybatis.example.service.IUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/save" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO save(@Validated(UserAddGroup.class)@RequestBody UserEntity userEntity){
        try {
            return new ResponseVO(userService.insertUser(userEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/login" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO login(@Validated(UserLoginGroup.class)@RequestBody UserEntity userEntity, HttpServletResponse response){
        try {
            return new ResponseVO(userService.loginUser(userEntity,response));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }


    @RequestMapping(value = "/getpage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @RequiresPermissions(logical = Logical.AND, value = {"user/getpage"})
    public ResponseVO getPage(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> param = EntityUtils.arraymapTobjectmap(request.getParameterMap());
            return new ResponseVO(userService.getPage(param));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/bindUserRoles" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO bindUserRoles(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("userId");
            String roleIds = request.getParameter("roleIds");
            List<String> roleIdsstr = ParamUtils.strTListstr(roleIds,",");
            return new ResponseVO(userService.bindUserRoles(userId,roleIdsstr));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }
}
