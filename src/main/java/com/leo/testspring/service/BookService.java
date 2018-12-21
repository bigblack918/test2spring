package com.leo.testspring.service;

import com.leo.testspring.dao.BookRepository;
import com.leo.testspring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService{

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        return  bookRepository.findAll(new Sort(Sort.Direction.ASC, "status"));
    }

    public List<Book> findBookByStatus(int status){
        return  bookRepository.findReport(status);
    }

    public Book findBookById(Long id){
        return  bookRepository.findBookById(id);
    }

    public Book saveBook(Book book){
        return  bookRepository.save(book);
    }

    public Book updateBook(Book book){
        return  bookRepository.save(book);
    }

    public void deleteById(Long id){
        //bookRepository.deleteById(id);
        bookRepository.deleteById(id);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }


}
