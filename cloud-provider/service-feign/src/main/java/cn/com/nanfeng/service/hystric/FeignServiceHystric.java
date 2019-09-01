package cn.com.nanfeng.service.hystric;

import cn.com.nanfeng.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 17:32
 */
@Component
public class FeignServiceHystric implements FallbackFactory<FeignService> {

    @Override
    public FeignService create(Throwable throwable) {
        return new FeignService(){
           @Override
           public String hello() {
               return "sorry";
           }
       };
    }
}
