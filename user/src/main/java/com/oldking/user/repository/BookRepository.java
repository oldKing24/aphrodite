package com.oldking.user.repository;

import com.oldking.user.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wangzhiyong
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {
    //Optional<Book> findById(String id);

}
