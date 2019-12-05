package cn.com.nanfeng.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-05 17:51
 */
@RestController
@RefreshScope
public class BusController {


    @Value("${foo}")
    String foo;

    @GetMapping(value = "/testBus")
    public String testBus(){
        return foo;
    }
}
