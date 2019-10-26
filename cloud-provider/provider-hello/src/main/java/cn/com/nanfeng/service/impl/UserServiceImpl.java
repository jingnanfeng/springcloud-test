package cn.com.nanfeng.service.impl;

import cn.com.nanfeng.mapper.DdUserMapper;
import cn.com.nanfeng.model.po.DdUser;
import cn.com.nanfeng.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-26 11:49
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private DdUserMapper userMapper;

    @Override
    public List<DdUser> getAllUser() {
       List<DdUser> userList = userMapper.selectAllUser();
       return userList;
    }
}
