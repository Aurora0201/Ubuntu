package com.javaframework.mybatis.test;

import com.javaframework.mybatis.bean.Car;
import com.javaframework.mybatis.utils.SessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void insertTest() {
        SqlSession sqlSession = SessionUtil.getSqlSession();

        /*Map<String, Object> map = new HashMap<>();
        map.put("carNum", "1005");
        map.put("brand", "BYD");
        map.put("guidePrice", 30.0);
        map.put("productTime", "2024-01-01");
        map.put("carType", "Electronic");*/

        int count = sqlSession.insert("insertCar", new Car(11L,1006, "BYD-1", 35.0, "2022-01-01", "Electronic"));

        System.out.println(count);
        sqlSession.commit();

    }

    @Test
    public void deleteTest() {
        SqlSession sqlSession = SessionUtil.getSqlSession();
        sqlSession.delete("deleteById",10);
        sqlSession.commit();
    }

    @Test
    public void updateTest() {
        SqlSession sqlSession = SessionUtil.getSqlSession();
        sqlSession.update("updateCar", new Car(11L, 1007, "BYD-H", 25.0, "2022-12-15", "Electronic"));
        sqlSession.commit();
    }

    @Test
    public void selectByIdTest() {
        SqlSession sqlSession = SessionUtil.getSqlSession();
        Object obj = sqlSession.selectOne("selectById",1);
        System.out.println(obj);
    }

    @Test
    public void selectAll() {
        SqlSession sqlSession = SessionUtil.getSqlSession();
        List<Object> cars = sqlSession.selectList("selectAll");
        cars.forEach(car -> System.out.println(car));
    }
}

