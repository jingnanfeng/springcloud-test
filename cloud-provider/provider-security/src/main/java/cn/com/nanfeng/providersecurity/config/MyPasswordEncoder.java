package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-21 12:56
 */
@Slf4j
@Service
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        log.info("加密时待加密的密码：[{}]",rawPassword.toString());
        return Md5Util.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.debug("校验时待加密的密码：[{}]",rawPassword.toString());
        String encrypt = Md5Util.encode(rawPassword.toString());
        log.debug("加密后的密码：[{}]",encrypt);
        log.debug("校验时已加密的密码：[{}]",encodedPassword);
        return encodedPassword.equalsIgnoreCase(encrypt);
    }
}
