package com.heiio.bookredis.model.blog;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {

    private Long id;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean published;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;
    private String[] tags;
    private int commentSize;

}
