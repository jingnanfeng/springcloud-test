package cn.com.nanfeng.web.controller;

import cn.com.nanfeng.model.po.DdUser;
import cn.com.nanfeng.service.IUserService;
import cn.com.nanfeng.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-26 11:52
 */
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/getAllUser")
    public List<DdUser> getAllUser(){
        List<DdUser> userList = userService.getAllUser();
        return userList;
    }
}
