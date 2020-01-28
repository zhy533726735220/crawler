package com.zhy.dao;

public interface ICrawlerDao {
    String selectNextAvailableLink();
    int updateLink(String url);
    int insertLink(String url);
    int isExistUrl(String url);
    int insertNews(String title, String content, String url);
}
