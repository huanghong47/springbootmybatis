package cn.hhfarcry.springbootmybatis.common.filemanager.txt.impl;

import java.io.File;
import java.io.IOException;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-03-28 10:08
 */
public interface ITxtService {
    public void readTxt();
    public File createTxt(String filepath);
    public void writerTxt() throws IOException;
}
