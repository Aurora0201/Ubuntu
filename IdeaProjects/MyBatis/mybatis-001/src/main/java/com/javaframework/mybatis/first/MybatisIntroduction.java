package com.javaframework.mybatis.first;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MybatisIntroduction {
    public static void main(String[] args) throws IOException {
        //get the SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //get the SqlSessionFactory
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");//get resources from root directory by default
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //get the SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //execute SQl
        int count = sqlSession.insert("insertCar");
        System.out.println("insert items:" + count);
        //manually commit
        sqlSession.commit();
    }
}
