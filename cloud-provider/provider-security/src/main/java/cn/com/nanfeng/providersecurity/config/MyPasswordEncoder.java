package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-30 16:54
 */
public class MyPasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LoggerFactory.getLogger(MyPasswordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        logger.info("加密时待加密的密码：[{}]",rawPassword.toString());
        return Md5Util.encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        logger.debug("校验时待加密的密码：[{}]",rawPassword.toString());
        String encrypt = Md5Util.encode(rawPassword.toString());
        logger.debug("加密后的密码：[{}]",encrypt);
        logger.debug("校验时已加密的密码：[{}]",encodedPassword);

        return encodedPassword.equalsIgnoreCase(encrypt);
    }
}
