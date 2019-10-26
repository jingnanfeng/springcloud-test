package cn.com.nanfeng.book;

import cn.com.nanfeng.model.po.Book;
import cn.com.nanfeng.service.IBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-09-06 10:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {
    private static final Logger logger = LoggerFactory.getLogger(BookTest.class);

    @Resource
    private IBookService bookService;

    @Test
    public void testBook(){
        List<Book> bookList = bookService.getAllBook();
        for (Book book : bookList) {
            System.out.println(book.getBName());
        }
    }


}
