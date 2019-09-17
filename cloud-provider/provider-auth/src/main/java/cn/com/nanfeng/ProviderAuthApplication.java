package cn.com.nanfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-08 14:37
 */
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class ProviderAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAuthApplication.class);
    }
}
