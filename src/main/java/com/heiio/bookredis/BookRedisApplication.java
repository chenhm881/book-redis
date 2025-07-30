package com.heiio.bookredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRedisApplication.class, args);
    }

}
