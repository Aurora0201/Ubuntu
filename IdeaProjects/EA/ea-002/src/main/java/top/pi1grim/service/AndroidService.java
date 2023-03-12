package top.pi1grim.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AndroidService {
    byte[] getCode() throws IOException;

    boolean detectLogin();

    void crawlData(int len);

    void invokePython() throws IOException;
}
