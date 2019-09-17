package cn.com.nanfeng.filter;

import cn.com.nanfeng.controller.VaildateCodeController;
import cn.com.nanfeng.exception.VaildateCodeException;
import cn.com.nanfeng.model.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-09 22:37
 */
@Component
public class VaildateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.endsWithIgnoreCase("/login",httpServletRequest.getRequestURI())
        && StringUtils.endsWithIgnoreCase(httpServletRequest.getMethod(),"post")){
            try {
                validateCode(new ServletWebRequest(httpServletRequest));
            }catch (VaildateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, VaildateCodeController.SESSION_KEY_IMAGE_CODE);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new VaildateCodeException("验证码不能为空！");
        }
        if (codeInSession == null) {
            throw new VaildateCodeException("验证码不存在！");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(servletWebRequest, VaildateCodeController.SESSION_KEY_IMAGE_CODE);
            throw new VaildateCodeException("验证码已过期！");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new VaildateCodeException("验证码不正确！");
        }
        sessionStrategy.removeAttribute(servletWebRequest, VaildateCodeController.SESSION_KEY_IMAGE_CODE);

    }
}