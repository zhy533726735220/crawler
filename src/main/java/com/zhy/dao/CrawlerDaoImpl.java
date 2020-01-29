package com.zhy.dao;

import com.zhy.vo.News;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CrawlerDaoImpl implements ICrawlerDao {

    private SqlSessionFactory sqlSessionFactory;

    public CrawlerDaoImpl() {
        String resource = "db/mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String selectNextAvailableLink() {
        String result = null;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            result = sqlSession.selectOne("com.zhy.dao.mapper.selectNextAvailableLink");
        }
        return result;
    }

    @Override
    public int updateLink(String url) {
        int result = 0;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            result = sqlSession.update("com.zhy.dao.mapper.updateLink", url);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public int insertLink(String url) {
        int result = 0;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            result = sqlSession.insert("com.zhy.dao.mapper.insertLink", url);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public int isExistUrl(String url) {
        int result = 0;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            result = sqlSession.selectOne("com.zhy.dao.mapper.isExistUrl", url);
        }
        return result;
    }

    @Override
    public int insertNews(String title, String content, String url) {
        int result = 0;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            News vo = new News();
            vo.setTitle(title);
            vo.setContent(content);
            vo.setUrl(url);
            result = sqlSession.insert("com.zhy.dao.mapper.insertNews", vo);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public List<News> getNews() {
        List<News> news =  null;
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            news = sqlSession.selectList("com.zhy.dao.mapper.getNews");
        }
        return news;
    }
}
