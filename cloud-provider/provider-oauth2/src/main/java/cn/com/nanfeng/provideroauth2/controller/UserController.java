package cn.com.nanfeng.provideroauth2.controller;

import cn.com.nanfeng.provideroauth2.service.impl.RedisCodeService;
import cn.com.nanfeng.provideroauth2.smscode.SmsCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-24 17:57
 */
@RestController
public class UserController {

    @Resource
    private RedisCodeService redisCodeService;

    @GetMapping("index")
    public Object index(Authentication authentication){
        return authentication;
    }



    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) throws Exception {
        SmsCode smsCode = createSMSCode();
        redisCodeService.save(smsCode, new ServletWebRequest(request), mobile);
        // 输出验证码到控制台代替短信发送服务
        System.out.println("手机号" + mobile + "的登录验证码为：" + smsCode.getCode() + "，有效时间为120秒");
    }

    private SmsCode createSMSCode() {
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code);
    }
}
