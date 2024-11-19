package simpleshop.shop.service;

import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

public interface UserService {
    public User regist(User user);
    public User findUser(String userId);
    public User changeUserInfo(String userId, UserForm userForm);
    public void withdraw(String userId);
    public void changeGrade(String userId, Grade grade);
    public User login(String userId, String userPassword);
}
