package cn.hhfarcry.springbootmybatis.common.base.service;

import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.utils.UUIDUtils;
import cn.hhfarcry.springbootmybatis.common.base.vo.PageVO;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: 业务层通用方法
 * @author: huanghong
 * @date: 2019-01-10 11:09
 */
public class BaseService <E extends BaseEntity>{

    protected BaseDao baseDao;

    @Autowired
    private SecurityService service;

    protected void init(){

    }


    //分页查询
    public Page<E> getPage(Map<String, Object> param){
        PageVO pageVO = PageVO.getPageVO(param);
        Page<E> page = PageHelper.startPage(pageVO.getPagenum(), pageVO.getPagesize(), pageVO.getField()+ " " +pageVO.getOrder()).doSelectPage(()-> baseDao.selectByEntity(param));
        return page;
    }

    /**
     * 设置操作用户
     * @param baseEntity
     */
    public void buildUser(BaseEntity baseEntity){
        if(ParamUtils.isBlank(baseEntity)){
            return;
        }else{
            UserEntity user = service.getUser();
            if(ParamUtils.isBlank(baseEntity.getId())){
                if(ParamUtils.isBlank(baseEntity.getIsdeleted())){
                    baseEntity.setIsdeleted(1);
                }
                if(ParamUtils.isBlank(baseEntity.getCreatetime())){
                    baseEntity.setCreatetime(new Date());
                }
                if(ParamUtils.isBlank(baseEntity.getCreateuserid())){
                    baseEntity.setCreateuserid(ParamUtils.isBlank(user)?-1:user.getId());
                }
            }else{
                baseEntity.setUpdatetime(new Date());
                baseEntity.setUpdateuserid(ParamUtils.isBlank(user)?-1:user.getId());
            }
        }
    }
}



