package cn.com.nanfeng.provider.service;

import cn.com.nanfeng.provider.service.HelloFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 16:17
 */
@Component
public class HelloFallback implements FallbackFactory<HelloFeign> {

    @Override
    public HelloFeign create(Throwable throwable) {
        return new HelloFeign() {
            @Override
            public String hello() {
                return null;
            }
        };
    }
}
