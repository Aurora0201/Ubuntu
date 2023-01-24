package FileOperationTest;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("MyFile");
            String s = "123456789";
            fw.write(s);
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
