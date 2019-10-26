package cn.com.nanfeng.mapper;

import cn.com.nanfeng.model.po.DdUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DdUserMapper {
    int deleteByPrimaryKey(String ddId);

    int insert(DdUser record);

    int insertSelective(DdUser record);

    DdUser selectByPrimaryKey(String ddId);

    int updateByPrimaryKeySelective(DdUser record);

    int updateByPrimaryKey(DdUser record);

    List<DdUser> selectAllUser();
}