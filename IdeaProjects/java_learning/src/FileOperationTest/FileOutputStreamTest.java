package FileOperationTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("MyFile");
            String s = "我是中国人";
            byte[] bytes = s.getBytes();
            /*
            * Output in this way, if the file don't exist, the method will create a new file,
            * else the method will override the file, the old content will be lost
            * */

            fos.write(bytes);
//            fos.write(bytes, 0, 2); //Write a part of bytes

//            fos = new FileOutputStream("MyFile", true);//Append at the end of file


            /*
             * Remember flush the Stream in the end
             * */
            fos.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
