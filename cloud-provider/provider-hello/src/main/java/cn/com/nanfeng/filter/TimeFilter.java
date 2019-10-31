package cn.com.nanfeng.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 15:44
 */
/*@Component
@WebFilter(urlPatterns = {"/*"})*/
public class TimeFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("开始执行过滤器");
        Long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("【过滤器】耗时"+(new Date().getTime() - start));
        logger.info("过滤器执行结束");
    }

    @Override
    public void destroy() {
        logger.info("过滤器销毁");
    }
}
