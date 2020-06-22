package com.heiio.bookredis.controller;

import com.heiio.bookredis.model.Article;
import com.heiio.bookredis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/myRedis")
    public String getArticle(String id) {
        Article article = articleService.getArticle(id);
        return article.getAuthor();
    }
}
