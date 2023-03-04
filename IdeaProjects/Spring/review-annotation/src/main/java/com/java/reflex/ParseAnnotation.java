package com.java.reflex;

import com.java.annotation.Component;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * scan the whole package with given package name
 * @author binjunkai
 * @version 1.0
 */
public class ParseAnnotation {
    private final static Map<String, Object> singletonObjects = new HashMap<>();
    public static void main(String[] args) {
        String packageName = "com.java.bean";
        //turn package name into file path
        String packagePath = packageName.replaceAll("\\.","/");
//        System.out.println(packagePath);
        URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
        String path = url.getPath();
        //get file from url
        File file = new File(path);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            String className = packageName + "." + f.getName().split("\\.")[0];
//            System.out.println(className);
            try {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(Component.class)){
                    Component component = clazz.getDeclaredAnnotation(Component.class);
                    String id = component.value();
                    //instantiate the bean if the bean has the annotation
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    //put into cache
                    singletonObjects.put(id, obj);
                }

            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        });
        System.out.println(singletonObjects);
    }
}
