package com.javaframework.mybatis.first;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Write a complete version MyBatis program
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class MybatisComplete {
    public static void main(String[] args) {
        SqlSession session = null;
        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("mybatis-config.xml"));
            session = factory.openSession();
            //SQL statements
            int count = session.insert("insertCar");
            System.out.println(count);
            //transaction commit
            session.commit();
        } catch (IOException e) {
            if (session != null) {
                session.rollback();
            }
            throw new RuntimeException(e);
        }finally{
            if (session != null) {
                session.close();
            }
        }
    }
}
