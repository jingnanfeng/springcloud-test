package cn.com.nanfeng.controller;
import cn.com.nanfeng.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 16:06
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;


    @GetMapping("/hello")
    public String hello(){
        return feignService.hello();
    }
}
