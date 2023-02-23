import com.emotion.analysis.util.DataParseUtil;
import com.emotion.analysis.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UtilTest {
    @Test
    public void openTest() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        SqlSessionUtil.close();
    }

    @Test
    public void parseTest() {
        String json = "[{\"content\":\"今天很开心\",\"probability\":0.92,\"status\":\"False\",\"time\":\"2023-02-20 00:00:00\",\"username\":\"杰哥\"}," +
                "{\"content\":\"今天很伤心\",\"probability\":0.26,\"status\":\"True\",\"time\":\"2023-02-20 00:00:00\",\"username\":\"张三\"}]";
        DataParseUtil.parseJSON(json);
    }

}
