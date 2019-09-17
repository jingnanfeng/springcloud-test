package cn.com.nanfeng.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 15:23
 */
@RestController
public class TestController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

    @GetMapping("/authentication/require")
    @ResponseStatus
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response, "/login2.html");
            }
        }
        return "访问的资源需要身份认证";
    }

    @GetMapping("/login")
    public String login(){
        return "<h1>欢迎</h1>";
    }

    @GetMapping("index")
    public Object index(Authentication authentication){
        return authentication;
    }



}
