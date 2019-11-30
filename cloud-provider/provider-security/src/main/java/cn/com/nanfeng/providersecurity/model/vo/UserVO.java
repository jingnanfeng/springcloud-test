package cn.com.nanfeng.providersecurity.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-30 15:53
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = -7653876946901782904L;
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String sex;

    private String phone;

    private String email;
}
