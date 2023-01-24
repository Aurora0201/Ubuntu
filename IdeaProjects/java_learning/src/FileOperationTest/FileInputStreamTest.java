package FileOperationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            int readCot;
            byte[] bytes = new byte[1 << 2];
            fis = new FileInputStream("");
//            fis.skip(5); // Skip bytes
            while ((readCot = fis.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, readCot));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
