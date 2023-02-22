package com.emotion.analysis.service.impl;

import com.emotion.analysis.bean.Content;
import com.emotion.analysis.mapper.ContentMapper;
import com.emotion.analysis.service.ContentService;
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
    @Override
    /*
      select all items from DB
     */
    public List<Content> getAllContent() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
        return mapper.selectAllContent();
    }
}
