package cn.com.nanfeng.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-06 11:18
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Mono<String> fallback(){
        return Mono.just("fallback");
    }

}
