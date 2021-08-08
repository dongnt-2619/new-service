package com.tannv.system.productservice.controller;

import com.tannv.system.productservice.config.Constants;
import com.tannv.system.productservice.exception.ResourceNotFoundException;
import com.tannv.system.productservice.model.News;
import com.tannv.system.productservice.repository.NewsRepository;
import com.tannv.system.productservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author nguyen.van.tan (David Black)
 * @project IntelliJ IDEA
 * @created 07/06/2021 - 15:47
 */
@RestController
@RequestMapping("/api/new")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private final NewsService newsService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<News> findAll(Pageable pageable) {
//    JwtAuthenticationToken token =
//        (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//    log.debug(token.getToken().getClaims().toString());
        return newsRepository.findAll(pageable).getContent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody News news) {
        news.setCreated_date(new Date());
        news = newsRepository.save(news);
        log.debug(news.getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public News getByID(@PathVariable("id") String id) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("New not found with id :" + id));
        return existingNews;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNew(@Valid @RequestBody News news, @PathVariable("id") String id) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("New not found with id :" + id));
        newsService.update(news, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNew(@PathVariable("id") String id) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("New not found with id :" + id));
        newsRepository.delete(existingNews);
    }

    @PutMapping("/publish/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void publishNew(@PathVariable("id") String id) {
        newsService.publish(id);
    }

    @PutMapping("/unpublish/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void unpublishNew(@PathVariable("id") String id) {
        newsService.unpublish(id);
    }

    @GetMapping("/publish")
    public ResponseEntity<List<News>> getNewsPublish(Pageable pageable){
        Page<News> newsPublish = newsRepository.findNewsByStatus(Constants.NEW_STATE.PUBLISH , pageable);
        return ResponseEntity.ok().body(newsPublish.getContent());
    }
    @GetMapping("/search")
    public ResponseEntity<List<News>> search(MultiValueMap<String, String> queryParams , Pageable pageable){
        Page<News> searchNews = newsRepository.findAllByTitleLikeOrAuthorLike(queryParams , pageable);
        return ResponseEntity.ok().body(searchNews.getContent());
    }
    @PostMapping("/test")
    public Boolean test(){
        return true;
    }
    @GetMapping("/test1")
    public Boolean test1() {
        return true;
    }
}
