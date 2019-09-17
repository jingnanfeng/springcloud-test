package cn.com.nanfeng.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-11 22:24
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;

    public SmsAuthenticationToken(String mobile){
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }
    public SmsAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
        if (isAuthenticated){
            throw new IllegalArgumentException("Cannot set this token to trusted - " +
                    "use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
    public void eraseCredentials(){
        super.eraseCredentials();
    }

}
