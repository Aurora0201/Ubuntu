package com.emotion.analysis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.emotion.analysis.bean.Content;
import com.emotion.analysis.mapper.ContentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

/**
 * read the csv file in the resource dir
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
public class CsvReadUtil {
    private CsvReadUtil() {

    }

    /**
     * delete the old date,read the csv file and save the data to mysql
     */
    public static void readCsv() {
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
}
