package com.emotion.analysis.mapper;

import com.emotion.analysis.bean.Content;

import java.util.List;

/**
 * content mapper
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
public interface ContentMapper {
    void insertContent(Content content);

    void deleteContent();

    List<Content> selectAllContent();
}
