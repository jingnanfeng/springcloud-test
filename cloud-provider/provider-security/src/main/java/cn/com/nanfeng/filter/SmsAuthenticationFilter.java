package cn.com.nanfeng.filter;

import cn.com.nanfeng.config.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-12 17:18
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String MOBILE_KEY = "mobile";

    private String mobileRarameter = MOBILE_KEY;

    private boolean postOnly = true;

    public SmsAuthenticationFilter(){
        super(new AntPathRequestMatcher("/login/mobile","POST"));
    }

    protected String obtainMobile(HttpServletRequest request){
        return request.getParameter(mobileRarameter);
    }

    protected void setDetails(HttpServletRequest request,
                                SmsAuthenticationToken authRequest){
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileRarameter(String mobileRarameter){
        Assert.hasText(mobileRarameter,"mobile parameter must not be empty or null");
        this.mobileRarameter = mobileRarameter;
    }

    public void setPostOnly(boolean postOnly){
        this.postOnly = postOnly;
    }

    public final String getMobileRarameter(){
        return mobileRarameter;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
      if (postOnly && !request.getMethod().equals("POST")){
          throw new AuthenticationServiceException(
                  "Authentication method not supported:"+request.getMethod());
      }
      String mobile = obtainMobile(request);

      if (mobile == null){
          mobile = "";
      }

      mobile = mobile.trim();

      SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);

      setDetails(request,authRequest);

      return this.getAuthenticationManager().authenticate(authRequest);
    }

}
