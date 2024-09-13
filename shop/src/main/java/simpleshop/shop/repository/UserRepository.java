package simpleshop.shop.repository;

import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;

public interface UserRepository {
    //회원가입
    public User join(User user);
    //회원정보 조회
    public User findUserById(String userId);
    //회원정보 수정
    public User updateUserInfo(String userId, UserForm userForm);
    //회원정보 삭제
    public void deleteUser(String userId);
    public void clearStore();
}
