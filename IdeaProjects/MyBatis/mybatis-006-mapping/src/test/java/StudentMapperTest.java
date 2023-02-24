import com.java.framework.bean.Student;
import com.java.framework.mapper.StudentMapper;
import com.java.framework.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class StudentMapperTest {
    @Test
    public void selectByIdTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1);
        students.forEach(System.out::println);
        sqlSession.close();
    }
}
