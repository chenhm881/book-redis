package com.heiio.bookredis.service;

import com.heiio.bookredis.mapper.BlogMapper;
import com.heiio.bookredis.model.blog.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@CacheConfig(cacheManager = "blogCacheManager")
public class BlogService {

    @Value("http://localhost:9080/createBlog")
    private String createBlogEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BlogMapper blogMapper;

    @Cacheable(cacheNames = {"blog"})
    public List<Blog> getBlogList() {
        System.out.println("It is run on getBlogList");
        return blogMapper.findBlogList();
    }

    public String create(Blog blog) {
        return restTemplate.postForObject(createBlogEndpoint, blog, String.class);
    }
}
