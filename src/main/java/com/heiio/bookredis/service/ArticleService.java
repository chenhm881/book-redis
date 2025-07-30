package com.heiio.bookredis.service;


import com.heiio.bookredis.mapper.ArticleMapper;
import com.heiio.bookredis.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.function.Function;

@CacheConfig(cacheManager = "articleCacheManager")
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    //@Cacheable(cacheNames = "article", key="#id")
    public Article getArticle(Integer id) {
        System.out.println("It is run on getArticle");
        Article article = articleMapper.select(id);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> fn = executorService.submit(() -> {
            return "";
        });
        String name;
        try {
            name = fn.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Cacheable(cacheNames = "article", key="#id")
    public Article getCacheArticle(Integer id) {
        System.out.println("It is run on getArticle");
        Article article = articleMapper.findById(id);
        return article;
    }

}
