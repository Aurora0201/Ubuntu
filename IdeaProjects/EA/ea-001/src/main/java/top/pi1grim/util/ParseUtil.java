package top.pi1grim.util;

import java.io.*;
import java.util.List;

public class ParseUtil {

    public static void writeCsv(List<String> nicknames, List<String> contents) throws IOException {
        String delimiter = ",";
        FileWriter fw = new FileWriter(new File("data.csv"));
        int size = nicknames.size();

        for (int i = 0; i < size; i++) {
            System.out.println("Write : " + i);
            fw.write(nicknames.get(i) + delimiter + contents.get(i) + "\n");
        }

        fw.flush();
        fw.close();
    }

}
