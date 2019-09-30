package cn.com.nanfeng.provideroauth2.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-24 15:59
 */
@Data
public class MyUser implements Serializable {

    private static final long serialVersionUID = 7969808072640005432L;

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialNonExpired = true;

    private boolean enabled = true;


}
