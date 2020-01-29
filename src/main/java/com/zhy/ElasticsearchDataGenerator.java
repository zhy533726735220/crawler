package com.zhy;

import com.zhy.dao.CrawlerDaoImpl;
import com.zhy.dao.ICrawlerDao;
import com.zhy.vo.News;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticsearchDataGenerator {
    public static void main(String[] args) {
        ICrawlerDao iCrawlerDao = new CrawlerDaoImpl();
        List<News> newsByDB = iCrawlerDao.getNews();

        try (RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")))) {
            for (News vo : newsByDB) {
                IndexRequest request = new IndexRequest("news");
                Map<String, Object> map = new HashMap<>();
                map.put("content", vo.getContent());
                map.put("url", vo.getUrl());
                map.put("title", vo.getTitle());
                request.source(map, XContentType.JSON);
                IndexResponse response = client.index(request, RequestOptions.DEFAULT);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
