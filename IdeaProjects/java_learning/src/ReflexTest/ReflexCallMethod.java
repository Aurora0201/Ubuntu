package ReflexTest;

import OverrideTest.toString.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReflexCallMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, InstantiationException {
        ResourceBundle bundle = ResourceBundle.getBundle("ReflexTest/resource");
        String className = bundle.getString("name");
        /*
        * The way to call method in using reflex mechanism
        * First, get the class and create an object of the class
        * Second, get the method from the class
        * Three, pass object and parameters
        * */
        //Get the class
        Class c = Class.forName("ReflexTest.User");
        //Creat an object
//        Object obj = c.cast(new Object());
        Object obj = c.newInstance();
        //Get the method from the class

        Method m = c.getMethod("getName");

        //Call the method

        Object o = m.invoke(obj);
        System.out.println(o);
    }
}
