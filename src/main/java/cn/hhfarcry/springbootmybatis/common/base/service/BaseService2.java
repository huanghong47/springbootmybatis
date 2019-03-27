package cn.hhfarcry.springbootmybatis.common.base.service;

import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.common.base.dao.BaseDao2;
import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.common.base.entity.BaseEntity2;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.base.vo.PageVO;
import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
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
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 11:09
 */
public class BaseService2<E extends BaseEntity2>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected BaseDao2 baseDao2;

    @Autowired
    private SecurityService service;

    protected void init(){

    }


    //分页查询
    public Page<E> getPage(Map<String, Object> param){
        PageVO pageVO = PageVO.getPageVO(param);
        Page<E> page = PageHelper.startPage(pageVO.getPagenum(), pageVO.getPagesize(), pageVO.getField()+ " " +pageVO.getOrder()).doSelectPage(()-> baseDao2.selectByEntity(param));
        return page;
    }

    /**
     * 设置操作用户,只有在做增删改时才调该方法,新增时baseEntity是new过的，删和改时baseEntity是数据库的数据
     * @param baseEntity2
     */
    public void buildUser(BaseEntity2 baseEntity2){
        if(ParamUtils.isBlank(baseEntity2)){
            return;
        }else{
            UserEntity user = service.getUser();
            if(ParamUtils.isBlank(baseEntity2.getUuid())){
                if(ParamUtils.isBlank(baseEntity2.getIsDeleted())){
                    baseEntity2.setIsDeleted(0);
                }
                if(ParamUtils.isBlank(baseEntity2.getCreateTime())){
                    baseEntity2.setCreateTime(new Date());
                }
                if(ParamUtils.isBlank(baseEntity2.getCreateUserUuid())){
//                    baseEntity2.setCreateUserUuid(ParamUtils.isBlank(user)?0:user.getId());
                }
            }else{
                baseEntity2.setUpdateTime(new Date());
//                baseEntity2.setUpdateUserId(ParamUtils.isBlank(user)?0:user.getId());
            }
        }
    }
}



