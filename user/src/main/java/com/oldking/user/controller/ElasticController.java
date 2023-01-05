package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.user.domain.Book;
import com.oldking.user.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author wangzhiyong
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class ElasticController {
    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public AphroditeResponse<String> Save(@RequestBody Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setCreateTime(new Date());
        bookService.save(book);
        log.info("test");
        return AphroditeResponse.success(book.getId());
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable String id) {
        Optional<Book> opt = bookService.findById(id);
        Book book = opt.get();
        log.warn(book.toString());
        return book;
    }
}
