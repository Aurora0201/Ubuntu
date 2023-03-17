package com.mapper;

import com.bean.Data;

import java.util.List;

public interface DataMapper {
    void insert(Data data);

    List<Data> selectAll();
}
