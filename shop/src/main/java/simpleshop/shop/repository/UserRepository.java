package simpleshop.shop.repository;

import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.Order;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

import java.util.Map;
import java.util.UUID;

public interface UserRepository {
    //회원가입
    public User join(User user);
    //회원정보 조회
    public User findUserById(String userId);
    //회원정보 수정
    public User updateUserInfo(String userId, UserForm userForm);
    //회원정보 삭제
    public void userGrade(String userId, Grade grade);
    public void deleteUser(String userId);
    public void addOrder(User user, UUID orderId);
    public void removeOrder(User user,  UUID orderId);
    public void clearStore();
}
