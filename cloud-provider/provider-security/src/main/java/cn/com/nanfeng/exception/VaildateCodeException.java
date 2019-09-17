package cn.com.nanfeng.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-09 22:35
 */
public class VaildateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 5022575393500654458L;

    public VaildateCodeException(String msg) {
        super(msg);
    }
}
