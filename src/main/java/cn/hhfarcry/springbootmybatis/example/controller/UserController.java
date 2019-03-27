package cn.hhfarcry.springbootmybatis.example.controller;


import cn.hhfarcry.springbootmybatis.common.base.controller.BaseController;
import cn.hhfarcry.springbootmybatis.common.base.utils.EntityUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.utils.ValidateUtil;
import cn.hhfarcry.springbootmybatis.common.base.vo.ResponseVO;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import cn.hhfarcry.springbootmybatis.example.service.IUserService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: emsog
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


    /**
     * 添加注册用户
     * @param userEntity
     * @return
     */
    @PostMapping(value = "/add")
    @ResponseBody
//    @RequiresPermissions(logical = Logical.AND, value = {"user/save"})
    public ResponseVO save(@RequestBody UserEntity userEntity){
        try {
            if(ParamUtils.isBlank(userEntity.getUserName())
                    || ParamUtils.isBlank(userEntity.getPassword())
                    || ParamUtils.isBlank(userEntity.getPhone())){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            if(!ValidateUtil.validatePwd(userEntity.getPassword())){
                return new ResponseVO("密码规则不符合(6-16位字母,数字)");
            }
            if(!ValidateUtil.validatePhone(userEntity.getPhone())){
                return new ResponseVO("手机号规则不符合");
            }
            return new ResponseVO(userService.insertUser(userEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 编辑用户
     * @param userEntity
     * @return
     */
    @PostMapping(value = "/modify")
    @ResponseBody
    //@RequiresPermissions(logical = Logical.AND, value = {"user/save"})
    public ResponseVO modify(@RequestBody UserEntity userEntity){
        try {
            if(ParamUtils.isBlank(userEntity.getId())
                    || ParamUtils.isBlank(userEntity.getUserName())
                    || ParamUtils.isBlank(userEntity.getPhone())){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            if(!ValidateUtil.validatePhone(userEntity.getPhone())){
                return new ResponseVO("手机号规则不符合");
            }
            return new ResponseVO(userService.updateUser(userEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 重置密码
     * @param request
     * @return
     */
    @PostMapping(value = "/reset")
    @ResponseBody
    //@RequiresPermissions(logical = Logical.AND, value = {"user/save"})
    public ResponseVO reset(HttpServletRequest request){
        try {
            if(ParamUtils.isBlank(ParamUtils.strTIntger(request.getParameter("id")))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(userService.reset(ParamUtils.strTIntger(request.getParameter("id"))));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @PostMapping(value = "/changepw")
    @ResponseBody
    //@RequiresPermissions(logical = Logical.AND, value = {"user/save"})
    public ResponseVO changepw(HttpServletRequest request){
        try {
            if(ParamUtils.isBlank(ParamUtils.strTIntger(request.getParameter("id")))
                    || ParamUtils.isBlank(request.getParameter("newpw"))
                    || ParamUtils.isBlank(request.getParameter("oldpw"))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            if(!ValidateUtil.validatePwd(request.getParameter("newpw"))){
                return new ResponseVO("密码规则不符合:6-16位字母,数字");
            }
            return new ResponseVO(userService.changepw(ParamUtils.strTIntger(request.getParameter("id"))
            ,request.getParameter("newpw"),request.getParameter("oldpw")));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 删除用户
     * @param request
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    //@RequiresPermissions(logical = Logical.AND, value = {"user/save"})
    public ResponseVO delete(HttpServletRequest request){
        try {
            if(ParamUtils.isBlank(ParamUtils.strTListstr(request.getParameter("ids"),","))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(userService.delete(ParamUtils.strTListstr(request.getParameter("ids"),",")));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 查询用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getpage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
//    @RequiresPermissions(logical = Logical.AND, value = {"user/getpage"})
    public ResponseVO getPage(HttpServletRequest request, HttpServletResponse response){
        try {
//            UserEntity userEntity = null;
//            System.out.println(userEntity.getId());
            Map<String,Object> param = EntityUtil.arraymapTobjectmap(request.getParameterMap());
            Page<UserEntity> result = userService.getPage(param);
            return new ResponseVO(result);
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 登录
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/login" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO login(@RequestBody UserEntity userEntity){
        try {
            if(ParamUtils.isBlank(userEntity.getUserName())
                    || ParamUtils.isBlank(userEntity.getPassword())){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(userService.loginUser(userEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 绑定用户角色
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/bindUserRoles" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO bindUserRoles(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("userId");
            String roleIds = request.getParameter("roleIds");
            List<String> roleIdsstr = ParamUtils.strTListstr(roleIds,",");
            if(ParamUtils.isBlank(roleIdsstr) || ParamUtils.isBlank(ParamUtils.strTIntger(userId))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(userService.bindUserRoles(userId,roleIdsstr));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }
}
