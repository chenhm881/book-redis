package com.heiio.bookredis.mapper;

import com.heiio.bookredis.model.blog.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogMapper {
    List<Blog> findBlogList();
}
