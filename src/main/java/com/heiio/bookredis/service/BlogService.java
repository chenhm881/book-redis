package com.heiio.bookredis.service;

import com.heiio.bookredis.mapper.BlogMapper;
import com.heiio.bookredis.model.blog.Blog;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheManager = "blogCacheManager")
public class BlogService {

    @Value("http://localhost:9080/createBlog")
    private String createBlogEndpoint;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Cacheable(cacheNames = {"blog"})
    public List<Blog> getBlogList() {
        System.out.println("It is run on getBlogList");
        return blogMapper.findBlogList();
    }

    @Cacheable(cacheNames = {"blog"})
    public void find() throws InterruptedException {
        blogMapper.find();
    }
}
