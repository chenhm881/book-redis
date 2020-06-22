package com.heiio.bookredis.service;


import com.heiio.bookredis.model.Article;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Cacheable(cacheNames = "article", key="#id")
    public Article getArticle(String id) {
        System.out.println("It is run on getArticle");
        Article article = new Article();
        article.setAuthor("Jack");
        article.setTitle("Mermaid");
        return article;
    }
}
