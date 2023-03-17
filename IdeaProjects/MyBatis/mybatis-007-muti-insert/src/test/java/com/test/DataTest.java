package com.test;

import com.bean.Data;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DataMapper;
import com.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataTest {
    @Test
    public void insertTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        List<Data> data = new ArrayList<>();
        data.add(new Data("123", "123"));
        data.add(new Data("123", "123"));
        data.add(new Data("345", "123"));
        data.add(new Data("345", "123"));
        data.add(new Data("567", "123"));
        data.add(new Data("567", "123"));
        data.forEach(mapper::insert);
        sqlSession.commit();
    }

    @Test
    public void pageTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        PageHelper.startPage(1, 4);
        List<Data> data = mapper.selectAll();
        System.out.println(data);

    }
}
