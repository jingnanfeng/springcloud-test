package cn.com.nanfeng.mapper;

import cn.com.nanfeng.model.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface BookMapper {

    int deleteByPrimaryKey(Integer bId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bId);

    int updateByPrimaryKeySelective(Book record);


    int updateByPrimaryKey(Book record);

    List<Book> selectAllBook();
}