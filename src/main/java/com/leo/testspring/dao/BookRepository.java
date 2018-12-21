package com.leo.testspring.dao;

import com.leo.testspring.model.Book;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookById(Long id);

    @Query(value = "select * from book where status = ?1", nativeQuery = true)
    List<Book> findReport(int status);
}
