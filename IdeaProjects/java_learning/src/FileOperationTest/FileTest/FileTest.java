package FileOperationTest.FileTest;

import java.io.File;
import java.io.IOException;

public class FileTest {
    /*
    * 1.Class File can't be used to read or write file
    * 2.File object stands for a directory or a file
    *
    * */
    public static void main(String[] args) {
        File file = new File("/home/binjunkai/Desktop/File");
        if (!file.exists()) {//Determined whether it exists
//            file.createNewFile();//Create a new file
            file.mkdir();//Create a new directory
        }
    }
}
