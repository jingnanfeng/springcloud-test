package cn.com.nanfeng.config;

import cn.com.nanfeng.filter.SmsCodeFilter;
import cn.com.nanfeng.filter.VaildateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 15:32
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private MyAuthehticationFailureHandler authehticationFailureHandler;
    @Resource
    private VaildateCodeFilter vaildateCodeFilter;
    @Resource
    private DataSource dataSource;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private SmsCodeFilter smsCodeFilter;
    @Resource
    private SmsAuthenticationConfig smsAuthenticationConfig;

   /* @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.addFilterBefore(vaildateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()//表单方式
                .loginPage("/authentication/require")//指定跳转登录页面的请求URL
                .loginProcessingUrl("/login")//对应登录页面form表单的action="/login"
                .successHandler(authenticationSuccessHandler)//处理登录成功
                .failureHandler(authehticationFailureHandler)//处理失败
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())//配置token持久化仓库
                .tokenValiditySeconds(3600)//过期时间，单位秒
                .userDetailsService(userDetailsService)//设置自动登录逻辑
                .and()
                .authorizeRequests()//授权配置
                .antMatchers("/authentication/require", "/login.html","/code/image").permitAll()//跳转到登录页面的请求不被拦截
                .anyRequest()//所有需求
                .authenticated()//都需要认证
                .and().csrf().disable();
    }
*/
    protected void configure(HttpSecurity http) throws Exception{
        http.addFilterBefore(vaildateCodeFilter,UsernamePasswordAuthenticationFilter.class)//添加验证码的过滤器
             .addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)//短信验证码校验过滤器
                .formLogin()//登录表单
                .loginPage("/authentication/require")//登录跳转的URL
                .loginProcessingUrl("/login")//处理表单登录的URL
                .successHandler(authenticationSuccessHandler)//成功
                .failureHandler(authehticationFailureHandler)//失败
                .and()
                .authorizeRequests()//授权配置
                .antMatchers("/authentication/require",
                        "/login2.html", "/code/image","/code/sms").permitAll()//无需认证的请求路径
                .anyRequest()//所有请求
                .authenticated()//都需要认证
                .and().csrf().disable()
                .apply(smsAuthenticationConfig);//将短信验证加到Spring Security中
    }

    /**
     * 密码加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceimpl(){
        return new UserDetailServiceImpl();
    }

    /**
     * token持久化
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }


}
