package ReflexTest;

import java.lang.reflect.Field;
import java.util.ResourceBundle;

public class
ReflexAccessField {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, NoSuchFieldException {
        ResourceBundle bundle = ResourceBundle.getBundle("ReflexTest/resource");
        String className = bundle.getString("name");
        /*
         * The way to access field in using reflex mechanism
         * First, get the class and create an object of the class
         * */
        //Get the class
        Class c = Class.forName(className);
        //Creat an object
        Object obj = c.cast(new Object());
        /*
        * Usually, we can't access fields with modifier "private",
        * but if we broke the encapsulation we can access the fields
        * */

        Field nameField = c.getDeclaredField("name");
        Field passwordField = c.getDeclaredField("password");

        nameField.setAccessible(true);
        passwordField.setAccessible(true);

        nameField.set(obj, "Bin");
        passwordField.set(obj, "root");

        System.out.println(nameField.get(obj));
        System.out.println(passwordField.get(obj));
    }
}
