package top.pi1grim.service.impl;

import jakarta.annotation.Resource;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pi1grim.bean.OriginData;
import top.pi1grim.mapper.AndroidMapper;
import top.pi1grim.service.AndroidService;
import top.pi1grim.util.AndroidUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class AndroidServiceImpl implements AndroidService {
    @Resource
    private AndroidMapper mapper;
    @Override
    public byte[] getCode() throws IOException {
        ByteArrayOutputStream bos = null;
        FileInputStream in = null;
        in = new FileInputStream(AndroidUtil.getQRFile());
        bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1 << 10];
        int len;
        while ((len = in.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        return bos.toByteArray();
    }

    @Override
    public boolean detectLogin() {
        long waitingTime = 30;//second
        long begin = System.currentTimeMillis();
        while (!AndroidUtil.isLogin() && System.currentTimeMillis() - begin <= waitingTime * 1000) {
            System.out.println("超时等待：" + (System.currentTimeMillis() - begin) / 1000.0);
        }
        return AndroidUtil.isLogin();
    }

    @Override
    public void crawlData(int len) {
        List<OriginData> data = AndroidUtil.getData(len);
        data.forEach(mapper::insert);
    }

    @Override
    public void invokePython() throws IOException {
        Runtime.getRuntime().exec("python3 /home/binjunkai/codes/predict.py");
    }
}
