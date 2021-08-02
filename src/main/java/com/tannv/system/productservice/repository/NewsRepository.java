package com.tannv.system.productservice.repository;

import com.tannv.system.productservice.model.News;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author nguyen.van.tan (David Black)
 * @project IntelliJ IDEA
 * @created 07/06/2021 - 15:46
 */
public interface NewsRepository extends MongoRepository<News, String> {
    Page<News> findAll(Pageable pageable);
}
