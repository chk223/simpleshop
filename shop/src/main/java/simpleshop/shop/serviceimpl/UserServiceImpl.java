package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.exception.user.UserAlreadyExistException;
import simpleshop.shop.exception.user.UserNotFoundException;
import simpleshop.shop.repository.UserRepository;
import simpleshop.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User regist(User user) {
        if(userRepository.findUserById(user.getUserId()) != null) {
            throw new UserAlreadyExistException(user.getUserId()+"는 이미 존재하는 아이디입니다.");
        }
        userRepository.join(user);
        return user;
    }

    @Override
    public User findUser(String userId) {
        User user = userRepository.findUserById(userId);
        if(user == null) {
            throw new UserNotFoundException(userId+"를 갖는 유저가 없습니다.");
        }
        return user;
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
        User findUser = userRepository.findUserById(userId);
        //아이디, 비밀번호 검증
        if(findUser == null) {
            throw new UserNotFoundException("없는 ID입니다.");
        }
        if(userId == null || userId.trim().isEmpty() ||
        userPassword == null || userPassword.trim().isEmpty()
        ||!findUser.getUserPassword().equals(userPassword)) {
            throw new UserNotFoundException("비밀전호 또는 아이디가 잘못되었습니다.");
        }
        return findUser;
    }

}
