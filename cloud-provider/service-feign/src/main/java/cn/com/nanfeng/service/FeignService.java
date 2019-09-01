package cn.com.nanfeng.service;

import cn.com.nanfeng.service.hystric.FeignServiceHystric;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 16:29
 */
@FeignClient(name = "privider-hello",fallbackFactory = FeignServiceHystric.class)
public interface FeignService {

    @RequestMapping("/hello")
    String hello();
}
