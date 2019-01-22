package cn.hhfarcry.springbootmybatis.common.upload;

import cn.hhfarcry.springbootmybatis.common.service.BaseService;
import cn.hhfarcry.springbootmybatis.common.utils.EntityUtils;
import cn.hhfarcry.springbootmybatis.common.utils.ParamUtils;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-21 14:52
 */
@Service
@Transactional
public class FileService extends BaseService<FileEntity> implements IFileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public String insertFile(FileEntity params) {
        super.buildUser(params);
        fileDao.insertByEntity(params);
//        List<String>getRelations = fileDao.getRelation(params.getId());
//        StringBuffer sb = new StringBuffer();
//        for (String getRelation : getRelations) {
//            sb.append(getRelation);
//            sb.append("/");
//        }
//        params.setFileUrl(ParamUtils.isNotBlank(sb)?sb.toString():"");
//        fileDao.updateByEntity(params);
        return "ok";
    }

    @Override
    public String insertFiles(List<FileEntity> params) {
        for (FileEntity param : params) {
            super.buildUser(param);
        }
        fileDao.insertByEntityBatch(params);
        return "ok";
    }

    @Override
    public Page<FileEntity> getPage(FileEntity params) {
        Map<String,Object> map = EntityUtils.entityTmap(params);
        return super.getPage(map);
    }
}
