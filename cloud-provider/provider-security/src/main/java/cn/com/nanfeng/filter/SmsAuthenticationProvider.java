package cn.com.nanfeng.filter;

import cn.com.nanfeng.config.SmsAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-17 16:38
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken)authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String)authenticationToken.getPrincipal());
        if (userDetails == null){
            throw new InternalAuthenticationServiceException("未找到与改手机号对应的用户");
        }
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService(){
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
}
