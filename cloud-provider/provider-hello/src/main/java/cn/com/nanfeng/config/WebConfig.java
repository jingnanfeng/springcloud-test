package cn.com.nanfeng.config;

import cn.com.nanfeng.filter.TimeFilter;
import cn.com.nanfeng.interceptor.TimeInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 17:00
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private TimeInterceptor timeInterceptor;

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(timeInterceptor);
    }

}
