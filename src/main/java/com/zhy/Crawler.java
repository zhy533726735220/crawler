package com.zhy;

import com.zhy.dao.CrawlerDaoImpl;
import com.zhy.dao.ICrawlerDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler extends Thread{

    private ICrawlerDao iCrawlerDao = new CrawlerDaoImpl();

    @Override
    public void run() {
        String url = "https://sina.cn";
        while (url != null) {
            url = this.getUrl();
            try {
                this.parseHtml(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private  void parseHtml(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        this.getContent(doc, url);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            if (this.hitUrl(linkHref)) {
                this.insertLink(linkHref);
            }
        }

    }

    private synchronized String getUrl() {
        String url = this.iCrawlerDao.selectNextAvailableLink();
        this.iCrawlerDao.updateLink(url);
        return url;
    }

    private synchronized void insertLink(String linkHref) {
        if (this.iCrawlerDao.isExistUrl(linkHref) < 1) {
            this.iCrawlerDao.insertLink(linkHref);
        }
    }

    private  void getContent(Document doc, String url) {
        Elements titleHtml = doc.getElementsByTag("title");
        Elements contentHtml = doc.select("section.art_content");
        if (contentHtml.size() > 0 && titleHtml.size() > 0) {
            System.out.println("url:" + url);
            String content = contentHtml.text();
            String title = titleHtml.text();
            System.out.println("标题：" + title);
            System.out.println("内容：" + content);
            this.iCrawlerDao.insertNews(title, content, url);
        }
    }

    private  boolean hitUrl(String linkHref) {
        return linkHref.contains("https://news.sina.cn") ||
        linkHref.contains("https://travel.sina.cn") ||
        linkHref.contains("https://finance.sina.cn") ||
        linkHref.contains("https://sports.sina.cn") ||
        linkHref.contains("https://ent.sina.cn") ||
        linkHref.contains("https://tech.sina.cn") ||
        linkHref.contains("https://video.sina.cn") ||
        linkHref.contains("https://photo.sina.cn") ||
        linkHref.contains("https://nba.sina.cn") ||
        linkHref.contains("https://edu.sina.cn/");
    }
}
