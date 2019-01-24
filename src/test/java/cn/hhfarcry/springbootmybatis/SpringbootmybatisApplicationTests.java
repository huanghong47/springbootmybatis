package cn.hhfarcry.springbootmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        try {
            String url = "localhost:8080/user/login";
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type","application/json");
            Map<String, String> params = new HashMap<>();
            params.put("userName","admin");
            params.put("password","123456");
            System.out.println(test03HTTP.HttpClientUtils.doPost(url,headers,params));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

