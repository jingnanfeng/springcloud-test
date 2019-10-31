package cn.com.nanfeng.service.impl;

import cn.com.nanfeng.mapper.BookMapper;
import cn.com.nanfeng.model.po.Book;
import cn.com.nanfeng.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-05 20:25
 */
@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private BookMapper bookMapper;


    @Override
    public List<Book> getAllBook() {
        List<Book> bookList = bookMapper.selectAllBook();
        return bookList;
    }

    @Override
    public Book update(Book book) {
        bookMapper.updateByPrimaryKeySelective(book);
        return bookMapper.selectByPrimaryKey(book.getBId());
    }

    @Override
    public int deleteBookById(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }
}
