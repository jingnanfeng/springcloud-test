package cn.com.nanfeng.web.controller;

import cn.com.nanfeng.model.po.Book;
import cn.com.nanfeng.service.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 11:38
 */
@RestController
public class BookController {

    @Resource
    private IBookService bookService;


    @GetMapping("/getAllBook")
    public List<Book> getAllBook(){
        List<Book> bookList = bookService.getAllBook();
        return bookList;
    }

    @GetMapping("/getBookById")
    public Book getBookById(Integer id){
        Book book = bookService.getBookById(id);
        return book;
    }

    @PostMapping("/update")
    public Book update(Book book){
        Book book1 =bookService.update(book);
        return book;
    }

    @PostMapping("/delete")
    public int delete(Integer id){
        int res = bookService.deleteBookById(id);
        return res;
    }

}
