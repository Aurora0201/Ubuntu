package top.pi1grim.service;

import top.pi1grim.model.User;

public interface UserService {
    User findUserById(Integer id);

    void addUser(User user);
}
