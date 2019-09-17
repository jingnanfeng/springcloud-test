package cn.com.nanfeng.controller;

import cn.com.nanfeng.model.ImageCode;
import cn.com.nanfeng.model.SmsCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-09 21:42
 */
@RestController
public class VaildateCodeController {

    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ImageCode imageCode = createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE,imageCode);
        ImageIO.write(imageCode.getImage(),"jpeg",response.getOutputStream());
    }

    private ImageCode createImageCode(){
        int width = 100; //验证码图片宽度
        int heigth = 36; //验证码图片长度
        int length = 4;//验证码位数
        int expireIn = 60;//验证码有效时间

        BufferedImage image = new BufferedImage(width,heigth,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();

        Random random = new Random();
        g.setColor(getRandColor(200,255));
        g.fillRect(0,0,width,heigth);
        g.setFont(new Font("Times New Roman",Font.PLAIN,25));
        g.setColor(getRandColor(100,200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(heigth);
            int x1 = random.nextInt(8);
            int y1 = random.nextInt(8);
            g.drawLine(x, y, x - x1, y - y1);
        }
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),
                    20 + random.nextInt(110)));
            g.drawString(rand,13 * i + 6,16);
        }
        g.dispose();
        return new ImageCode(image,sRand.toString(),expireIn);
    }

    private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc = random.nextInt(bc - fc);
        int b = fc = random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    /**
     * 设置短信验证
     */
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request,HttpServletResponse response,String mobile){
        SmsCode smsCode = createSmsCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_IMAGE_CODE+mobile,smsCode);
        //用输出代替短线验证
        System.out.println("您登录的验证码："+smsCode.getCode()+",有效时间为60秒");
    }

    private SmsCode createSmsCode(){
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code,60);
    }
}
