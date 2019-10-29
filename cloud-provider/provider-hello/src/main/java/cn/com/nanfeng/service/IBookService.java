package cn.com.nanfeng.service;

import cn.com.nanfeng.model.po.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-05 20:24
 */
@Repository
@CacheConfig(cacheNames = "book")
public interface IBookService {

    List<Book> getAllBook();

    @CachePut(key = "#p0.bId")
    Book update(Book book);

    @CacheEvict(key = "#p0",allEntries = true)
    int deleteBookById(Integer id);

    @Cacheable(key = "#p0")
    Book getBookById(Integer id);
}
