package cn.com.nanfeng.provideroauth2.service.impl;

import cn.com.nanfeng.provideroauth2.smscode.SmsCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-25 21:28
 */
@Service
public class RedisCodeService {

    private final static String SMS_CODE_PREFIX = "SMS_CODE:";

    private final static Integer TIME_OUT = 300;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存验证码到redis
     * @param smsCode
     * @param request
     * @param mobile
     * @throws Exception
     */
    public void save(SmsCode smsCode, ServletWebRequest request, String mobile) throws Exception{
        redisTemplate.opsForValue().set(Key(request,mobile),smsCode.getCode(),TIME_OUT, TimeUnit.SECONDS);
    }

    /**
     * 获取验证码
     * @param request
     * @param mobile
     * @return
     * @throws Exception
     */
    public String get(ServletWebRequest request,String mobile) throws Exception{
        return redisTemplate.opsForValue().get(Key(request,mobile));
    }

    /**
     * 移除验证码
     * @param request
     * @param mobile
     * @throws Exception
     */
    public void remove(ServletWebRequest request,String mobile) throws Exception{
        redisTemplate.delete(Key(request,mobile));
    }

    public String Key(ServletWebRequest request,String mobile) throws Exception{
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new Exception("请在请求头中设置devicdId");
        }
        return SMS_CODE_PREFIX+deviceId+ ":" + mobile;
    }

}
