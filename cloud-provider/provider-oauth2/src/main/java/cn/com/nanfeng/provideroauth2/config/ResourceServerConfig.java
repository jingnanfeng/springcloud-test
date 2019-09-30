package cn.com.nanfeng.provideroauth2.config;

import cn.com.nanfeng.provideroauth2.handler.MyAuthenticationFailureHandler;
import cn.com.nanfeng.provideroauth2.handler.MyAuthenticationSuccessHandle;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-24 18:02
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private MyAuthenticationSuccessHandle authenticationSuccessHandle;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.formLogin()//登录表单
                .loginProcessingUrl("/login")//处理登录表单的URL
                .successHandler(authenticationSuccessHandle)//失败
                .failureHandler(authenticationFailureHandler)//成功
                .and()
                .authorizeRequests()//授权配置
                .anyRequest()//所有请求
                .authenticated()//都需要认证
                .and()
                .csrf().disable();
    }
}
