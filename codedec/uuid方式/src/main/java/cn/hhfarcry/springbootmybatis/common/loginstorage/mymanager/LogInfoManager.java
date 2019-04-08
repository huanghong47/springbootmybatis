package cn.hhfarcry.springbootmybatis.common.loginstorage.mymanager;

import cn.hhfarcry.springbootmybatis.common.security.SecurityService;
import cn.hhfarcry.springbootmybatis.common.base.utils.ParamUtils;
import cn.hhfarcry.springbootmybatis.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 10:45
 */
@Service
public class LogInfoManager implements ILogInfoManager{

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LogInfoDao logInfoDao;

    @Override
    public void addLog(LogInfoEntity logInfoEntity) {
        UserEntity userEntity = securityService.getUser();
        if(ParamUtils.isNotBlank(userEntity)){
            logInfoEntity.setUserId(userEntity.getId()+"");
            logInfoEntity.setUserName(userEntity.getUserName());
        }
        logInfoEntity.setIp(securityService.getIP());
//        System.out.println("将日志存入数据库,日志内容如下: " + JSON.toJSONString(logInfoEntity));
        logInfoDao.insertLogInfo(logInfoEntity);
    }
}
