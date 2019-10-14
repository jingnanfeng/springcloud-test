package cn.com.nanfeng.provideroauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-04 10:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
}
