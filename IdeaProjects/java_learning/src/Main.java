import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader fileReader = new BufferedReader(new FileReader("data.csv"));
//        System.out.println(fileReader.readLine());
        String tmp;
        while ((tmp = fileReader.readLine()) != null) {
//            System.out.println(tmp);
            String[] split = tmp.split(",");
            System.out.println(split[0]);

        }
    }
}

