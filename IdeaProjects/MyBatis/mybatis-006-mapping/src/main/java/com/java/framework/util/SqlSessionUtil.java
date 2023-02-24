package com.java.framework.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Session Util
 * @author binjunkai
 * @version 2.0
 * @since 1.0
 */
public class SqlSessionUtil {
    //one SqlSessionFactory corresponds to one Database Object
    private static SqlSessionFactory sqlSessionFactory;
    //server-level ThreadLocal
    private static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();


    private SqlSessionUtil() {

    }

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get SqlSession from ThreadLocal
     * @return SqlSession object
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * close sqlSession and remove binding of sqlSession and currentThread
     */
    public static void close() {
        SqlSession sqlSession = local.get();
        if (sqlSession != null) {
            sqlSession.close();
            local.remove();
        }
    }
}
