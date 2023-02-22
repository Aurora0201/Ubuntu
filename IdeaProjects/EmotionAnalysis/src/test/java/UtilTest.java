import com.emotion.analysis.util.CsvReadUtil;
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
    public void readTest() {
        CsvReadUtil.readCsv();
    }

}
