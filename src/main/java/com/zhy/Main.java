package com.zhy;

import com.zhy.dao.CrawlerDaoImpl;
import com.zhy.dao.ICrawlerDao;

public class Main {
    public static void main(String[] args) {

        ICrawlerDao iCrawlerDao = new CrawlerDaoImpl();

        for (int i = 0; i < 4; i++) {
            new Crawler(iCrawlerDao).start();
        }
    }
}
