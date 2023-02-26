import com.java.framework.bean.Clazz;
import com.java.framework.bean.Student;
import com.java.framework.mapper.ClazzMapper;
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

    @Test
    public void selectByIdAssociate() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByIdAssociate(1);
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void selectByIdTwice() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByIdStep1(1);
        students.forEach(System.out::println);

    }

    @Test
    public void selectClazzByFirst() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCid(1001);
        System.out.println(clazz);
    }

    @Test
    public void selectClazzBySecond() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCid2(1001);
        System.out.println(clazz);
    }


}
