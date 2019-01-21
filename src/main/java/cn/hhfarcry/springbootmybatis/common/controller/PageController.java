package cn.hhfarcry.springbootmybatis.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springbootmybatis
 * @description: ${description}
 * @author: huanghong
 * @date: 2019-01-18 14:46
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String de(){
        return "redirect:/hello";
    }

    @RequestMapping("/hello")
    public String index(){
        return "hello";
    }
}
