package com.java.framework.mapper;

import com.java.framework.bean.Clazz;

import java.util.List;

public interface ClazzMapper {
    List<Clazz> selectByIdStep2(Integer id);

    Clazz selectByCid(Integer id);

    Clazz selectByCid2(Integer id);
}
