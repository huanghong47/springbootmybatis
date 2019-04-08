package cn.hhfarcry.springbootmybatis;

import cn.hhfarcry.springbootmybatis.common.filemanager.txt.impl.ITxtService;
import cn.hhfarcry.springbootmybatis.example.entity.StudentEntity;
import cn.hhfarcry.springbootmybatis.example.service.ISchoolService;
import cn.hhfarcry.springbootmybatis.example.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootmybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test01(){

    }

    @Autowired
    private ITxtService txtService;
    @Test
    public void test02() throws IOException {
        txtService.writerTxt();
    }


    @Autowired
    private IStudentService studentService;
    @Test
    public void test03()  {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("黄洪");
        studentEntity.setPhone("111111");
        studentService.add(studentEntity);
    }
    @Autowired
    private ISchoolService schoolService;
    @Test
    public void test04()  {

    }
}

