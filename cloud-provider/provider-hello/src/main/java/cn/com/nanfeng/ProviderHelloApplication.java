package cn.com.nanfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 14:22
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderHelloApplication {

    public static void main(String[] args){
        SpringApplication.run(ProviderHelloApplication.class);
    }

}
