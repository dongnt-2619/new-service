package com.tannv.system.productservice.repository;

import com.tannv.system.productservice.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyen.van.tan (David Black)
 * @project IntelliJ IDEA
 * @created 07/06/2021 - 15:46
 */

@Repository
public interface NewsRepository extends MongoRepository<News, String>{
    Page<News> findAll(Pageable pageable);
    Page<News> findNewsByStatus(Integer status , Pageable pageable);
    Page<News> findAllByTitleLike(String keyword , Pageable pageable);
}
