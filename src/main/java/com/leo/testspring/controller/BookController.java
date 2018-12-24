package com.leo.testspring.controller;

import com.leo.testspring.RabbiteSender;
import com.leo.testspring.model.Book;
import com.leo.testspring.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookService bookService;

    @Autowired
    RabbiteSender rabbiteSender;

    @Value("${Book.name}")
    String customizeBookName;

    /**
     * 查詢書單列表
     * @return
     */
    @GetMapping("/books")
    public ResponseEntity<?> getAll(){
        System.out.println("JoinPoint");
        return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }

    /**
     * 查詢一本書
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        return new ResponseEntity<Book>(bookService.findBookById(id), HttpStatus.OK);
    }

    /**
     * 查詢一本書
     * @param status
     * @return
     */
    @GetMapping("/books/status/{status}")
    public ResponseEntity<?> getBookByStatus(@PathVariable Integer status){
        return new ResponseEntity<List<Book>>(bookService.findBookByStatus(status), HttpStatus.OK);
    }

    /**
     * 新增書本
     * @param book
     * @return
     */
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        Book book1 = bookService.saveBook(book);
        rabbiteSender.sendObj(book);
        return new ResponseEntity<Book>(book1, HttpStatus.CREATED);
    }

    /**
     * 更新書本資訊
     * @param id
     * @param book
     * @return
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,  @RequestBody Book book){
        Book currentBook = bookService.findBookById(id);
        book.setId(id);
        convert(book, currentBook);
        Book book1 = bookService.updateBook(currentBook);
        return new ResponseEntity<Book>(book1, HttpStatus.OK);
    }

    private void convert(Book bookupdate, Book currentBook){
        String[] nullPropertyName = getNullPropertyName(bookupdate);
        BeanUtils.copyProperties(bookupdate, currentBook, nullPropertyName);
    }

    private String[] getNullPropertyName(Book bookupdate){
        BeanWrapper beanWrapper = new BeanWrapperImpl(bookupdate);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyList = new ArrayList<>();
        for(PropertyDescriptor pd: pds){
            String propertyName = pd.getName();
            if(beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyList.add(propertyName);
            }
        }
        return nullPropertyList.toArray(new String[nullPropertyList.size()]);
    }

    /**
     * 刪除一本書
     * @param id
     * @return
     */
    @Transactional
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteOneBook(@PathVariable Long id){
        bookService.deleteById(id);
        logger.info("ID {} is deleted!", id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * 刪除全部的書
     * @return
     */
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBook(){
        bookService.deleteAll();
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    /**
     * 顯示參數書名
     * @return
     */
    @GetMapping(value="/books/showCustomizeBookName", produces = "text/html; charset=utf-8")
    public ResponseEntity<String> showCustomizeBookName(){
        System.out.println(customizeBookName);
        return new ResponseEntity<String>(customizeBookName, HttpStatus.OK);
    }

}
