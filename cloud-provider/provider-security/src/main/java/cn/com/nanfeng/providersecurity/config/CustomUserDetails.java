package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.model.vo.UserToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-29 11:21
 */
public class CustomUserDetails implements UserDetails,Serializable {

    private static final long serialVersionUID = -5131412220591140064L;

    private UserToken userEntity;

    public CustomUserDetails(){
    }

    public CustomUserDetails(UserToken userEntity){
        this.userEntity = userEntity;
    }

    /**
     * 获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        //构建角色集合
        List<GrantedAuthority> authorityList = new ArrayList<>();
        return authorityList;
    }

    /**
     * 用于获取用户密码
     * @return
     */
    @Override
    public String getPassword(){
        return userEntity.getPassword();
    }

    /**
     * 用户获取用户名
     * @return
     */
    @Override
    public String getUsername(){
        return userEntity.getName();
    }

    /**
     * 返回boolean类型，用于判断账户是否过期，未过期返回true反之返回false
     * @return
     */
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    /**
     * 用户判断账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    /**
     * 用于判断用户凭证是否过没期，即密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    /**
     * 用于判断用户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserToken getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserToken userEntity) {
        this.userEntity = userEntity;
    }
}
