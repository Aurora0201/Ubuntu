import com.java.framework.bean.Student;
import com.java.framework.mapper.StudentMapper;
import com.java.framework.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TypeTest {
    @Test
    public void selectByIdTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectMultiConditionTest() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByMultiCondition("", 16, 'F');
        students.forEach(System.out::println);
    }

    @Test
    public void updateById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateById(new Student(1L,"Ma ShiWen",15,null,null,null));
        sqlSession.commit();
        SqlSessionUtil.close();
    }
}
