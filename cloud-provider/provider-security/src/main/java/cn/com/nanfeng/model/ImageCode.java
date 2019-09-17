package cn.com.nanfeng.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-09 21:37
 */
@Data
public class ImageCode {

    private BufferedImage image;

    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage image,String code,int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
