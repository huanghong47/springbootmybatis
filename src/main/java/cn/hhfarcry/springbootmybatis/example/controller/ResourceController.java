package cn.hhfarcry.springbootmybatis.example.controller;

import cn.hhfarcry.springbootmybatis.common.base.controller.BaseController;
import cn.hhfarcry.springbootmybatis.common.base.utils.EntityUtil;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.vo.ResponseVO;
import cn.hhfarcry.springbootmybatis.example.entity.ResourceEntity;
import cn.hhfarcry.springbootmybatis.example.service.IResourceService;
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
import java.util.Map;

/**
 * @program: emsog
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-16 11:20
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IResourceService resourceService;

    /**
     * 资源新增和编辑
     * @param resourceEntity
     * @return
     */
    @RequestMapping(value = "/save" ,method = {RequestMethod.POST})
    @ResponseBody
//    @RequiresPermissions(logical = Logical.AND, value = {"resource/save"})
    public ResponseVO save(@RequestBody ResourceEntity resourceEntity){
        try {
            if(ParamUtils.isBlank(resourceEntity.getResourceName())
                    || ParamUtils.isBlank(resourceEntity.getResourceType())
                    || ParamUtils.isBlank(resourceEntity.getResourceUrl())){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(resourceService.insertResource(resourceEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 资源列表查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getpage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
//    @RequiresPermissions(logical = Logical.AND, value = {"resource/getpage"})
    public ResponseVO getPage(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> param = EntityUtil.arraymapTobjectmap(request.getParameterMap());
            return new ResponseVO(resourceService.getPage(param));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 根据角色获取资源列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getResourcesByRoleId" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getResourcesByRoleId(HttpServletRequest request, HttpServletResponse response){
        try {
            String roleId = request.getParameter("roleId");
            if(ParamUtils.isBlank(ParamUtils.strTIntger(roleId))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(resourceService.getResourcesByRoleId(roleId));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    /**
     * 根据用户获取资源列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getResourcesByUserId" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getResourcesByUserId(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("userId");
            if(ParamUtils.isBlank(ParamUtils.strTIntger(userId))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(resourceService.getResourcesByUserId(userId));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }


    /**
     * 绑定前后端资源
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindfrontbackres" ,method = {RequestMethod.POST})
    @ResponseBody
    public ResponseVO bindfrontbackres(HttpServletRequest request){
        try {
            String frontId = request.getParameter("frontId");
            String backIds = request.getParameter("backIds");

            if(ParamUtils.isBlank(ParamUtils.strTIntger(frontId))
                    || ParamUtils.isBlank(ParamUtils.strTListintger(backIds,","))){
                return new ResponseVO(ResponseVO.MESSAGE_LAKE_PARAMETER);
            }
            return new ResponseVO(resourceService.bindfbresources(ParamUtils.strTIntger(frontId),ParamUtils.strTListintger(backIds,",")));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }
}
