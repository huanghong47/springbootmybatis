package cn.hhfarcry.springbootmybatis.example.controller;

import cn.hhfarcry.springbootmybatis.common.controller.BaseController;
import cn.hhfarcry.springbootmybatis.common.utils.EntityUtil;
import cn.hhfarcry.springbootmybatis.common.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.vo.ResponseVO;
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
 * @program: springbootmybatis
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

    @RequestMapping(value = "/save" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO save(@RequestBody ResourceEntity resourceEntity){
        try {
            return new ResponseVO(resourceService.insertResource(resourceEntity));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/getpage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getPage(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String,Object> param = EntityUtil.arraymapTobjectmap(request.getParameterMap());
            return new ResponseVO(resourceService.getPage(param));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/getResourcesByRoleId" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getResourcesByRoleId(HttpServletRequest request, HttpServletResponse response){
        try {
            String roleId = request.getParameter("roleId");
            return new ResponseVO(resourceService.getResourcesByRoleId(ParamUtils.strTIntger(roleId)));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }

    @RequestMapping(value = "/getResourcesByUserId" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseVO getResourcesByUserId(HttpServletRequest request, HttpServletResponse response){
        try {
            String userId = request.getParameter("userId");
            return new ResponseVO(resourceService.getResourcesByUserId(ParamUtils.strTIntger(userId)));
        } catch (Exception e) {
            logger.error("controller error at {} --> {}", this.getClass().getName(), e);
            return new ResponseVO(ResponseVO.MESSAGE_SYSTEM_ERROR);
        }
    }
}
