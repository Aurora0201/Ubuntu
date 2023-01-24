package FileOperationTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) {
        FileReader fr = null;
        String s = new String();
        try {
            fr = new FileReader("MyFile");
            char[] chars = new char[4];
            int readCot;
            while ((readCot = fr.read(chars)) != -1) {
                System.out.print(new String(chars, 0, readCot));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
