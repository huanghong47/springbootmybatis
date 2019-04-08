package cn.hhfarcry.springbootmybatis.common.filemanager.txt;

import cn.hhfarcry.springbootmybatis.common.filemanager.txt.impl.ITxtService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-03-28 10:08
 */
@Service
public class TxtService implements ITxtService {

    @Override
    public void readTxt() {

    }

    @Override
    public File createTxt(String filepath) {
        File newfile = new File(filepath);
        if(!newfile.exists()){
            try {
                newfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newfile;
    }

    @Override
    public void writerTxt() throws IOException {
        String a = "a";
        String b = "b";
        File newfile = createTxt("C:/Users/BJQ/Desktop/ccc.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter =  new FileWriter(newfile);
            fileWriter.write(a+"\r\n");
            fileWriter.write(b);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();

        }

    }
}
