package simpleshop.shop.service;

import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

public interface UserService {
    public User regist(User user);
    public User findUser(String userId);
    public User chaingeUserInfo(String userId, UserForm userForm);
    public void withdraw(String userId);
}
