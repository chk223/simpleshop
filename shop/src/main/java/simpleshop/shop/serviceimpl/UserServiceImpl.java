package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.repository.UserRepository;
import simpleshop.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User regist(User user) {
        return userRepository.join(user);
    }

    @Override
    public User findUser(String userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public User changeUserInfo(String userId, UserForm userForm) {
        User orgUser = userRepository.findUserById(userId);
        if(userForm.getUserPassword()==null) userForm.setUserPassword(orgUser.getUserPassword());
        return userRepository.updateUserInfo(userId, userForm);
    }

    @Override
    public void withdraw(String userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public void changeGrade(String userId, Grade grade) {

    }

    @Override
    public User login(String userId, String userPassword) {
        //아이디, 비밀번호 검증
        if(userId == null || userId.trim().isEmpty() ||
        userPassword == null || userPassword.trim().isEmpty()) {
            return null;
        }
        //유저 검색 검증
        User findUser = userRepository.findUserById(userId);
        if(findUser != null) {
            if(findUser.getUserPassword().equals(userPassword)) {
                return findUser;
            }
        }
        return null;
    }

}
