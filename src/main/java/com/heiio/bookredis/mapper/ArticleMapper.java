package com.heiio.bookredis.mapper;

import com.heiio.bookredis.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleMapper {
    Article select(@Param("id") int id);
    Article findById(@Param("id") int id);
    void update(@Param("article") Article article);
}
