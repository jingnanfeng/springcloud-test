package cn.com.nanfeng.web.controller;

import cn.com.nanfeng.exception.BusinessException;
import cn.com.nanfeng.service.IHelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 14:24
 */
@RestController
public class HelloController {

    @Resource
    private IHelloService helloService;

    @Value("${server.port}")
    public String value;

    @GetMapping("/hello")
    public String hello(){
        String date = LocalDate.now().toString();
        return "hello "+date+"端口号"+value;
    }

    @GetMapping("/error")
    public void errorTest(){
        helloService.testError();
    }
}
