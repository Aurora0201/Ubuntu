import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        Process exec = null;
        exec = Runtime.getRuntime().exec("python3 /home/binjunkai/repo/IdeaProjects/java_learning/resources/test.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String line = null;
        while ((line =  in.readLine()) != null) {
            System.out.println(line);
        }
    }

}