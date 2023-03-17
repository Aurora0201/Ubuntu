package top.pi1grim.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pi1grim.mapper.UserMapper;
import top.pi1grim.model.User;
import top.pi1grim.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;
    @Override
    public User findUserById(Integer id) {
        return mapper.selectById(id);
    }

    public void addUser(User user) {
        mapper.insert(user);
    }
}
