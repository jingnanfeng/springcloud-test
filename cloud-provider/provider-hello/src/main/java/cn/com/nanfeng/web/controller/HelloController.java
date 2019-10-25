package cn.com.nanfeng.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 14:24
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    public String value;

    @RequestMapping("/hello")
    public String hello(){
        String date = LocalDate.now().toString();
        return "hello "+date+"端口号"+value;
    }
}
