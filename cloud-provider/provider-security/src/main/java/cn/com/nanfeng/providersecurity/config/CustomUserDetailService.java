package cn.com.nanfeng.providersecurity.config;

import cn.com.nanfeng.providersecurity.mapper.UsersMapper;
import cn.com.nanfeng.providersecurity.model.po.Users;
import cn.com.nanfeng.providersecurity.model.vo.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-29 13:46
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Resource
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(">>>>>>>>>>>>>>>>>>登录用户名:[{}]<<<<<<<<<<<<<<<<<<<",username);
        //查询用户名和密码
        Users user = usersMapper.selectUserByName(username);
        if (user == null){
            logger.error("CustomUserDetailService -> loadUserByUsername - 进入方法{}");
            throw new BadCredentialsException("用户名不存");
        }
        //logger.info(">>>>>>>>>>>>>>>>>登录用户:[{}]<<<<<<<<<<<<<<<<<<", JSONUtils.toJSONString(user));
        //转换user对象
        UserToken userToken = new UserToken();
        userToken.setId(user.getId());
        userToken.setName(user.getName());
        userToken.setPassword(user.getPassword());
        userToken.setEmail(user.getEmail());
        userToken.setPhone(user.getPhone());
        //查询集合角色
        //暂未实现。。。
        return new CustomUserDetails(userToken);
    }
}
