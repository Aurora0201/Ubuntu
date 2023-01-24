package PathAndIOProperties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ClosedSelectorException;
import java.util.Properties;

public class IOProperties {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*String config = Thread.currentThread().getContextClassLoader().getResource("ClassName.properties").getPath();
        FileReader fr = new FileReader(config);
        Properties pro = new Properties();
        pro.load(fr);
        fr.close();*/

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("ClassName.properties");
        Properties pro = new Properties();
        pro.load(in);
        in.close();
//        System.out.println(pro.getProperty("ClassName"));
        String className = pro.getProperty("ClassName");
        System.out.println(className);
        /*
        * className = ReflexTest.User
        * */
        Class c = Class.forName(className);
//        Object obj = c.newInstance();
    }

}
