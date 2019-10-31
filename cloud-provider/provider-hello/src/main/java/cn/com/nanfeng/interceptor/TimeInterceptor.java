package cn.com.nanfeng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 17:13
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o) throws Exception{
        logger.info("处理拦截器之前");
        request.setAttribute("startTime",new Date().getTime());
        logger.info(((HandlerMethod)o).getBean().getClass().getName());
        logger.info(((HandlerMethod)o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
                            ModelAndView modelAndView) throws Exception{
        logger.info("开始处理拦截器");
        Long start = (Long)request.getAttribute("startTime");
        logger.info("【拦截器】耗时"+(new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o,
                                Exception e) throws Exception{
        logger.info("处理拦截器之后");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        logger.info("【拦截器】耗时"+(new Date().getTime() - start));
        logger.info("异常信息"+e);
    }


}
