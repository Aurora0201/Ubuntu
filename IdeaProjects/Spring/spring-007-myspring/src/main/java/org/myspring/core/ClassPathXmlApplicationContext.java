package org.myspring.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.spi.ObjectFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{
    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);
    private Map<String, Object> singletonObjects = new HashMap<>();
//    private Map<String, ObjectFactory>
    /**
     * parse xml file and instantiate bean
     * @param configPath the path of configuration from project root
     */
    public ClassPathXmlApplicationContext(String configPath) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(ClassLoader.getSystemClassLoader().getResourceAsStream(configPath));
            List<Node> nodes = document.selectNodes("//bean");
            nodes.forEach(node -> {
                Element element = (Element) node;
                //get id
                String id = element.attributeValue("id");
                logger.info("id = " + id);
                //get class name
                String className = element.attributeValue("class");
                logger.info("class = " + className);

                try {
                    Class<?> clazz = Class.forName(className);
                    Object bean = clazz.getDeclaredConstructor().newInstance();
                    singletonObjects.put(id, bean);
                    logger.info(singletonObjects.toString());
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            nodes.forEach(node -> {
                Element element = (Element) node;
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                //get tag property
                List<Element> properties = element.elements("property");
                properties.forEach(property ->{
                    String propertyName = property.attributeValue("name");
                    String methodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                    logger.info("property = " + propertyName);
                    //assign values to attributes
                    try {
                        Class<?> bean = Class.forName(className);
                        //get field type
                        Field field = bean.getDeclaredField(propertyName);
                        //get method
                        Method method = bean.getDeclaredMethod(methodName, field.getType());

                        //get value | ref
                        String value = property.attributeValue("value");
                        String ref = property.attributeValue("ref");
                        if (value != null) {
                        /*
                        * Announce:
                        * our spring framework only support a part of usual type:
                        * int long float double boolean char
                        * Integer Long Float Double Boolean Character
                        * String
                        * */
                            String propertySimpleName = field.getType().getSimpleName();
                            Object ActualValue = null;
                            switch (propertySimpleName) {
                                case "int" -> ActualValue = Integer.parseInt(value);
                                case "Integer" -> ActualValue = Integer.valueOf(value);
                                case "long" -> ActualValue = Long.parseLong(value);
                                case "Long" -> ActualValue = Long.valueOf(value);
                                case "boolean" -> ActualValue = Boolean.parseBoolean(value);
                                case "Boolean" -> ActualValue = Boolean.valueOf(value);
                                case "String" -> ActualValue = value;
                            }
                        }else{
                            method.invoke(singletonObjects.get(id),singletonObjects.get(ref));
                        }
                        //invoke method
                    } catch (ClassNotFoundException | NoSuchMethodException |
                             IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }

                });

            });

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
