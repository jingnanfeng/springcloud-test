package cn.com.nanfeng.providersecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-29 9:56
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

}
