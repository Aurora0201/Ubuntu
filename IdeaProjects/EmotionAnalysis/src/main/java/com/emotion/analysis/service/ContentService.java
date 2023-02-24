package com.emotion.analysis.service;

import com.emotion.analysis.bean.Content;

import java.util.List;

/**
 * interface ContentService
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
public interface ContentService {
    List<Content> getAllContents();

    int saveContents(String json);
}
