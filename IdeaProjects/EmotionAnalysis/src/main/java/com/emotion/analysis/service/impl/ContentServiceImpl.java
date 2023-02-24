package com.emotion.analysis.service.impl;

import com.emotion.analysis.bean.Content;
import com.emotion.analysis.mapper.ContentMapper;
import com.emotion.analysis.service.ContentService;
import com.emotion.analysis.util.DataParseUtil;
import com.emotion.analysis.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * implements interface ContentService
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class ContentServiceImpl implements ContentService {
    /**
     * select all items from DB
     * @return list of all items
     */
    @Override
    public List<Content> getAllContents() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
        return mapper.selectAllContent();
    }

    /**
     * delete old data,parse json and insert into mysql
     * @param json json
     * @return the amount of item
     */
    @Override
    public int saveContents(String json) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);

//        delete old data
//        mapper.deleteContent();

        List<Content> contents = DataParseUtil.parseJSON(json);
        contents.forEach(mapper::insertContent);
        sqlSession.commit();
        return contents.size();
    }
}
