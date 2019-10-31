package cn.com.nanfeng.web.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-30 14:15
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            //接收地址
            message.setTo("liutao@afcat.com.cn");
            //标题
            message.setSubject("一封简单的邮件");
            //内容
            message.setText("使用Springboot发送简单的邮件。");
            javaMailSender.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sentHtmlEmail")
    public String sentHtmlEmail(){
        MimeMessage message = null;
        try {
           message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            //接收地址
            helper.setTo("liutao@afcat.com.cn");
            //标题
            helper.setSubject("一封HTML格式的邮件");
            //带HTML格式的内容
            StringBuffer sb = new StringBuffer("<p style='color:#42b893'>使用Springboot发送HTML格式的邮件</p>");
            helper.setText(sb.toString(),true);
            javaMailSender.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/sendAttachmentsMail")
    public String sendAttachmentsMail(){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            //接收地址
            helper.setTo("liutao@afcat.com.cn");
            //标题
            helper.setSubject("一封带附件的邮件");
            //内容
            helper.setText("附件");
            //传入附件
            FileSystemResource file = new FileSystemResource(new File("E:/aaa.txt"));
            helper.addAttachment("aaa.txt",file);

            javaMailSender.send(message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
