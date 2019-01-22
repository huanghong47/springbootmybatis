package cn.hhfarcry.springbootmybatis.common.upload;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-21 14:52
 */
public interface IFileService {
    String insertFile(FileEntity params);
    String insertFiles(List<FileEntity>params);
    Page<FileEntity> getPage(FileEntity params);
}
