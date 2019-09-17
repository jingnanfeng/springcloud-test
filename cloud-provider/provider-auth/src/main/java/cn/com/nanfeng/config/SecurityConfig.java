package cn.com.nanfeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-08 16:08
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //配置内存中两个用户
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("user_authorization_code")
                .password("123456").authorities("USER").build());
        userDetailsManager.createUser(User.withUsername("user_password")
                .password("123456").authorities("USER").build());
        auth.userDetailsService(userDetailsManager);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        //允许访问/oauth授权接口
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .formLogin().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();
    }
    //配置密码解码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
