package com.java.review.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class doReflex {
    public static void main(String[] args)throws Exception {
        String className = "com.java.review.reflex.User";

        String fieldName = "name";
        Class<?> clazz = Class.forName(className);
        Object user = clazz.getDeclaredConstructor().newInstance();
        //get method name
        String methodName = "set" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);

        //get method type
        Field field = clazz.getDeclaredField(fieldName);
//        Class<?> type = field.getType();
        Method setMethod = clazz.getDeclaredMethod(methodName, field.getType());

        setMethod.invoke(user, "123");
        System.out.println(user);
    }
}
