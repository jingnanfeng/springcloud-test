/*
package cn.com.nanfeng.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-06 10:20
 *//*

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        //使用Hystrix
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p
                    .path("/get")
                    .filters(f -> f.addRequestHeader("hello","world"))
                    .uri(httpUri))
                */
/*.route(p -> p
                    .host("*.hystrix.com")
                    .filters(f -> f
                        .hystrix(config -> config
                            .setName("mycmd")
                            .setFallbackUri("forward:/fallback")))
                    .uri(httpUri))*//*

                .build();
    }

}
*/
