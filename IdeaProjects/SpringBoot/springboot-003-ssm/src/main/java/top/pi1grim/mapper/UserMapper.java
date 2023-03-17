package top.pi1grim.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.pi1grim.model.User;

@Mapper
public interface UserMapper {
    void insert(User user);
    User selectById(Integer id);
}
