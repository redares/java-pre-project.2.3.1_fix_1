package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    User get(Long id);
    void delete(Long id);
    void update(User user);
    List<User> getListUsers();
}
