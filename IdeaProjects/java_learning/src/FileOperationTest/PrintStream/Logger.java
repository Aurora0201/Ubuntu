package FileOperationTest.PrintStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void record(String msg) {
        Date nowTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("y/M/d H:m:s");
        PrintStream ps = null;
        try {
            //append:true means it will write at the end of the file instead of overriding the file
            ps = new PrintStream(new FileOutputStream("log", true));
            System.setOut(ps);
            nowTime = new Date();
            System.out.println(sdf.format(nowTime) + " " + msg);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class LoggerTest {
    public static void main(String[] args) {
        Logger.record("第一次使用了日志记录！");
        Logger.record("我喜欢日志记录");
    }
}