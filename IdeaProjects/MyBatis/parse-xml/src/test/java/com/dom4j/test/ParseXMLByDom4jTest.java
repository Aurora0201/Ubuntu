package com.dom4j.test;

import org.apache.ibatis.session.SqlSession;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import util.SqlSessionUtil;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {
    @Test
    public void testParseMybatisConfig() throws DocumentException {
        //get SAXReader object
        SAXReader reader = new SAXReader();
        //get InputStream
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        //get document object
        Document document = reader.read(is);
        //get root
        Element root = document.getRootElement();
//        System.out.println(root.getName());
        //get identifier "environments"
        String xpath = "//environments";
        Element environments  = (Element) root.selectSingleNode(xpath);
//        System.out.println(environments);

        //get attributes
//        System.out.println(environments.attributeValue("default"));
        String defaultEnvironment = environments.attributeValue("default");
        //get environment whose id equals "development"
        xpath = "//environment[@id='" + defaultEnvironment + "']";
        Element environment = (Element) environments.selectSingleNode(xpath);
        //get the type of transactionManager
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
//        System.out.println(transactionType);

        //get dataSource and property "type"
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");

//        System.out.println(dataSourceType);

        //get the subElements of dataSource
        List<Element> elements = dataSource.elements();
        elements.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            System.out.println(name + ":" + value);
        });

    }


    @Test
    public void utilTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

    }
}
