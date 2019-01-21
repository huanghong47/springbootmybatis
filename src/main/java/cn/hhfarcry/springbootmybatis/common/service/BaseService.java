package cn.hhfarcry.springbootmybatis.common.service;

import cn.hhfarcry.springbootmybatis.common.dao.BaseDao;
import cn.hhfarcry.springbootmybatis.common.entity.BaseEntity;
import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
import cn.hhfarcry.springbootmybatis.common.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.common.utils.UUIDUtils;
import cn.hhfarcry.springbootmybatis.common.vo.PageVO;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-10 11:09
 */
public class BaseService <E extends BaseEntity>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
     * 设置操作用户,只有在做增删改时才调该方法,新增时baseEntity是new过的，删和改时baseEntity是数据库的数据
     * @param baseEntity
     */
    public void buildUser(BaseEntity baseEntity){
        if(ParamUtils.isBlank(baseEntity)){
            return;
        }else{
            UserEntity user = service.getUser();
            //构建uuid
            if(ParamUtils.isBlank(baseEntity.getIsDeleted())){
                baseEntity.setIsDeleted(0);
            }
            if(ParamUtils.isBlank(baseEntity.getUuid())){
                baseEntity.setUuid(UUIDUtils.newUUID());
            }
            //status默认为0
            if(ParamUtils.isBlank(baseEntity.getStatus())){
                baseEntity.setStatus(0);
            }
            if(ParamUtils.isBlank(baseEntity.getCreateTime())){
                baseEntity.setCreateTime(new Date());
            }
            if(ParamUtils.isBlank(baseEntity.getCreateUserUuid())){
                baseEntity.setCreateUserUuid(user.getId()+"");
//                baseEntity.setCreateUserUuid(currUser.getUUID);
            }
            if(ParamUtils.isBlank(baseEntity.getUpdateTime())){
                baseEntity.setUpdateTime(new Date());
            }
            if(ParamUtils.isBlank(baseEntity.getUpdateUserUuid())){
                baseEntity.setUpdateUserUuid(user.getId()+"");
            }
        }
    }

}


