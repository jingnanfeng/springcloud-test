package cn.com.nanfeng.provideroauth2.smscode;

import lombok.Data;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-25 21:34
 */
public class SmsCode {

    private String code;

    public SmsCode(String code) {
        this.code = code;
    }
    public SmsCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
