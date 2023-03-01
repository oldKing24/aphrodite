package com.oldking.user.service;

import com.oldking.user.domain.Book;
import com.oldking.user.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author wangzhiyong
 */
//@Service
public class BookService {
//    @Autowired
    private BookRepository bookRepository;

//    public Optional<Book> findById(String id) {
//        return bookRepository.findById(id);
//    }
//
//    public Book save(Book blog) {
//        return bookRepository.save(blog);
//    }
//
//    public void delete(Book blog) {
//        bookRepository.delete(blog);
//    }
//
//    public Optional<Book> findOne(String id) {
//        return bookRepository.findById(id);
//    }
//
//    public List<Book> findAll() {
//        return (List<Book>) bookRepository.findAll();
//    }

}
