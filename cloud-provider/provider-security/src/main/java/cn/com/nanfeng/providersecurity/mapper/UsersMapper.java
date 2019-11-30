package cn.com.nanfeng.providersecurity.mapper;

import cn.com.nanfeng.providersecurity.model.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    Users selectUserByName(String name);
}