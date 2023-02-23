package com.emotion.analysis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.emotion.analysis.bean.Content;
import com.emotion.analysis.mapper.ContentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

/**
 * parse data from ".csv" or json
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
public class DataParseUtil {
    private DataParseUtil() {

    }

    /**
     * delete the old date,read the csv file and save the data to mysql
     */
    public static void parseCsv() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
        BufferedReader bufferedReader = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mapper.deleteContent();
        try {
//            bufferedReader = new BufferedReader(new FileReader("src/main/resources/data.csv"));
            bufferedReader = new BufferedReader(Resources.getResourceAsReader("data.csv"));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                String[] args = tmp.split(",");
                Date date = sdf.parse(args[1]);
                mapper.insertContent(new Content(args[0], date, args[2], Double.parseDouble(args[3]), args[4]));
                sqlSession.commit();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SqlSessionUtil.close();
    }

    /**
     * parse JSON and save to mysql
     */
    public static void parseJSON(String text) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ContentMapper mapper = sqlSession.getMapper(ContentMapper.class);
        mapper.deleteContent();
        JSONArray jsons = JSON.parseArray(text);
        jsons.forEach(json -> {
            JSONObject jsonObject = JSONObject.parseObject(json.toString());
            Content content = (Content) JSON.to(Content.class, jsonObject);
//            System.out.println(content);
            mapper.insertContent(content);
        });
        sqlSession.commit();
        SqlSessionUtil.close();
    }
}
