package cn.com.nanfeng.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-11 16:35
 */
@Data
public class SmsCode {

    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code,LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     * 判断是否过期
     * @return
     */
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
