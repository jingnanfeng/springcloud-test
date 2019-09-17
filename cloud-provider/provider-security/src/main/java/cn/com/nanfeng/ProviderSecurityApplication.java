package cn.com.nanfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 15:16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class ProviderSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderSecurityApplication.class);
    }
}
