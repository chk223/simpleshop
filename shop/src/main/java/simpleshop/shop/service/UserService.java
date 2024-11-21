package simpleshop.shop.service;

import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

public interface UserService {
    /**회원가입*/
    public User regist(User user);
    /**유저 정보 반환*/
    public User findUser(String userId);
    /**유저정보 변경*/
    public User changeUserInfo(String userId, UserForm userForm);
    /**회원탈퇴*/
    public void withdraw(String userId);
    /**등급 변경*/
    public void changeGrade(String userId, Grade grade);
    /**로그인*/
    public User login(String userId, String userPassword);
}
