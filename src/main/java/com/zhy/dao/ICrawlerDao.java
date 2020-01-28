package com.zhy.dao;

public interface ICrawlerDao {
    public String selectNextAvailableLink();
    public int updateLink(String url);
    public int insertLink(String url);
    public int isExistUrl(String url);
    public int insertNews(String title, String content, String url);
}
