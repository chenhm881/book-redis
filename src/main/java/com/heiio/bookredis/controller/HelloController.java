package com.heiio.bookredis.controller;

import com.heiio.bookredis.model.Article;
import com.heiio.bookredis.model.blog.Blog;
import com.heiio.bookredis.service.ArticleService;
import com.heiio.bookredis.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/myRedis")
    public String getArticle(Integer id) {
        Article article = articleService.getArticle(id);
        return article.getAuthor();
    }

    @RequestMapping("/article/{id}")
    public String insertArticle(@PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        return article.getAuthor();
    }

    @RequestMapping("/article")
    public String getCacheArticle(Integer id) {
        Article article = articleService.getCacheArticle(id);
        return article.getTitle();
    }


    @RequestMapping("/bloglist")
    public ResponseEntity<List<Blog>> getBlogList() {
       List<Blog> blogList = blogService.getBlogList();
        blogList = blogList.stream().map(blog -> {
            redisTemplate.opsForZSet().add("tag", "tag1", 4);
            redisTemplate.opsForZSet().add("tag", "tag2", 3);
            redisTemplate.opsForZSet().add("tag", "tag3", 5);
            blog.setTags((String[]) redisTemplate.opsForZSet().rangeByScore("tag", 3, 4).toArray(new String[0]));
            return blog;
       }).collect(Collectors.toList());
       ResponseEntity<List<Blog>> responseEntity = new ResponseEntity<>(blogList, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/createBlog")
    public String create(@RequestBody Blog blog) {
        return blogService.create(blog);
    }

}
