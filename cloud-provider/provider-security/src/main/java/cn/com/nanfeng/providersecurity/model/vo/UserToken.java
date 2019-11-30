package cn.com.nanfeng.providersecurity.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-29 14:33
 */
@Data
public class UserToken implements Serializable {

    private static final long serialVersionUID = -8747935966027016540L;
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private String sex;

    private String phone;

    private String email;

}
