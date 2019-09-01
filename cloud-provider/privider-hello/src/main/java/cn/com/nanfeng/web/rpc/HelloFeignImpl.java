package cn.com.nanfeng.web.rpc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-01 15:53
 */
@RestController
public class HelloFeignImpl {

    @GetMapping("/api/hello")
    public String hello(){
        String date = LocalDate.now().toString();
        return "hello "+date;
    }
}
