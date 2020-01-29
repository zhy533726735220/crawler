package com.zhy.dao;

import com.zhy.vo.News;

import java.util.List;

public interface ICrawlerDao {
    String selectNextAvailableLink();

    int updateLink(String url);

    int insertLink(String url);

    int isExistUrl(String url);

    int insertNews(String title, String content, String url);

    List<News> getNews();
}
