package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Session Util
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class SqlSessionUtil {
    //one SqlSessionFactory corresponds to one Database Object
    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionUtil() {

    }
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
