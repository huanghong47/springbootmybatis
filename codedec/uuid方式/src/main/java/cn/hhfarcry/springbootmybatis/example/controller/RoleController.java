package cn.hhfarcry.springbootmybatis.example.controller;


import cn.hhfarcry.springbootmybatis.common.base.controller.BaseController;
import cn.hhfarcry.springbootmybatis.common.base.utils.EntityUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.vo.ResponseVO;
import cn.hhfarcry.springbootmybatis.example.entity.RoleEntity;
import cn.hhfarcry.springbootmybatis.example.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:20
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRoleService roleService;

    /**
     * 新增或编辑角色
     * @param roleEntity
     * @return
     */
    @RequestMapping(value = "/save" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO save(@RequestBody RoleEntity roleEntity){
        try {
            if(ParamUtils.isBlank(roleEntity.getRoleName())){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(roleService.insertRole(roleEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }


    /**
     * 角色查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getpage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getPage(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> param = EntityUtil.arraymapTobjectmap(request.getParameterMap());
            return new ResponseVO(roleService.getPage(param));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 绑定角色资源
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/bindroleResources" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO bindroleResources(HttpServletRequest request, HttpServletResponse response){
        try {
            String roleId = request.getParameter("roleId");
            String resourceIds = request.getParameter("resourceIds");
            List<String> resourceIdsstr = ParamUtils.strTListstr(resourceIds,",");
            if(ParamUtils.isBlank(resourceIdsstr) || ParamUtils.isBlank(ParamUtils.strTIntger(roleId))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(roleService.bindroleResources(roleId,resourceIdsstr));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 根据用户id获取角色列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getRolesByUserId" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getRolesByUserId(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("userId");
            if(ParamUtils.isBlank(ParamUtils.strTIntger(userId))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(roleService.getRolesByUserId(userId));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

}
