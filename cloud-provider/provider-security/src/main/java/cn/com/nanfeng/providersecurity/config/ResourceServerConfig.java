package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.handler.MyAuthenticationSuccessHandler;
import cn.com.nanfeng.providersecurity.handler.MyAuthentioncationFailHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-30 18:37
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSucessHandler;
    @Autowired
    private MyAuthentioncationFailHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
            //表单登录
            .formLogin()
            //处理登录成功
            .successHandler(authenticationSucessHandler)
            //处理登录失败
            .failureHandler(authenticationFailureHandler)
            //授权请求
            .and()
            .authorizeRequests()
            //排除url
            .antMatchers("/code/sms").permitAll()
            //其余的url都需要认证
            .anyRequest().authenticated()
            //允许跨站攻击
            .and()
            .headers().frameOptions().disable()
            .and()
            .cors();
    }

}
