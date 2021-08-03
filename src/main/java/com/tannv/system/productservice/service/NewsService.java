package com.tannv.system.productservice.service;

import com.tannv.system.productservice.config.Constants;
import com.tannv.system.productservice.model.News;
import com.tannv.system.productservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public void publish(String id) {
        News existNews = newsRepository.findById(id).get();
        existNews.setStatus(Constants.NEW_STATE.PUBLISH);
        newsRepository.save(existNews);
    }

    public void unpublish(String id) {
        News existNews = newsRepository.findById(id).get();
        existNews.setStatus(Constants.NEW_STATE.UNPUBLISH);
        newsRepository.save(existNews);
    }

    public void update(News news, String id) {
        News existNews = newsRepository.findById(id).get();
        existNews.setTitle(news.getTitle());
        existNews.setTags(news.getTags());
        existNews.setImage(news.getImage());
        existNews.setStatus(news.getStatus());
        existNews.setDescription(news.getDescription());
        existNews.setAuthor(news.getAuthor());
        existNews.setModified_date(new Date());
        newsRepository.save(existNews);
    }
}
