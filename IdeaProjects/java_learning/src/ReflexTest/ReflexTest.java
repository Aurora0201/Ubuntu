package ReflexTest;

import OverrideTest.toString.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReflexTest {
    /*
    * What is the reflex mechanism:
    * 1.We can operate on .class file in Java through reflex mechanism
    * 2.Reflex mechanism is very flexible,
    * so we can modify everything through modifying config file instead of code
    *
    * Important package in Java:
    * "java.lang.Class" stands for bytecode file
    * "java.lang.reflect.Method stand" stands for methods in bytecode file
    * "java.lang.reflect.Constructor" stands for constructor in bytecode file
    * "java.lang.reflect.Field" stands for field in bytecode file
    *
    * */

    /*
    * How to get the bytecode of a class?
    * 1.Class c = Class.forName("Path")
    * 2.Class c = reference.getClass()
    * 3.Class c = (any type).class //Any type has a field called class, we can get the bytecode by accessing this field
    *
    * */

    public static void main(String[] args) {

        /*
        * forName() is a static method:
        *   1.Parameter is a string;
        *   2.Return bytecode of class
        *   3.Bytecode is stored in method area
        *
        * What happened in running method forName()?
        * 1.Class loading and the static code block running
        *
        * Method forName() is related to following JDBC learning
        * */
        Class c1 = null;
        try {
            c1 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String s = "123";
        Class c2 = s.getClass();
        /*
        * Return true, which proves that their memory address are the same
        * */
        System.out.println(c1 == c2);
        System.out.println(String.class == c2);
        Class c3 = Student.class;
    }
}

/*
* Create an object with using reflex mechanism
* When we study the framework,
* we will find that the reflection mechanism is very useful and convenient
* */

class FlexibleReflex {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        FileReader fr = new FileReader("src/ClassName.properties");
        Properties pro = new Properties();
        pro.load(fr);
        fr.close();
        String className = pro.getProperty("ClassName");
        Class c = Class.forName(className);
        Object o = c.cast(new Object());
    }
}
