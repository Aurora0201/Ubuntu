package ResourceBundleTest;

import java.util.ResourceBundle;

/*
* 1.ResourceBundle only can be used to bundle ".properties" file,
* and the file should be put in directory "src".
* 2.Only when we want to read ".properties" file we can use this method
* */
public class ResourceBundleTest {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("ClassName");// Don't add suffix ".properties"
        /*
        * Get resource from package
        * */
        ResourceBundle resource = ResourceBundle.getBundle("ResourceBundleTest/resource");// Don't add suffix ".properties"

        System.out.println(bundle.getString("ClassName"));
        System.out.println(resource.getString("name"));

    }
}
