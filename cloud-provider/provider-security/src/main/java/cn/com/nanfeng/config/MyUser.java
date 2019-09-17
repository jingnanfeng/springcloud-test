package cn.com.nanfeng.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 15:53
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 3497935890006858541L;

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private  boolean enable = true;
}
