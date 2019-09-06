package cn.com.nanfeng.provider.service;

import cn.com.nanfeng.provider.service.fallback.HelloFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 15:55
 */
@FeignClient(value = "PRIVIDER-HELLO",fallbackFactory = HelloFallback.class)
public interface HelloFeign {

    @GetMapping("/api/hello")
    String hello();


}
