package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.exception.user.UserAlreadyExistException;
import simpleshop.shop.exception.user.UserNotFoundException;
import simpleshop.shop.repository.CartRepository;
import simpleshop.shop.repository.UserRepository;
import simpleshop.shop.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

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
        userRepository.userGrade(userId,grade);
    }

    @Override
    public User login(String userId, String userPassword) {
//        log.info("서비스 로직 로그인 시도: userId={}, userPassword={}", userId,userPassword);
        // 입력 값 검증
        if (userId == null || userId.trim().isEmpty()) {
            throw new UserNotFoundException("아이디를 입력해주세요.");
        }
        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new UserNotFoundException("비밀번호를 입력해주세요.");
        }
        User findUser = userRepository.findUserById(userId);
        //아이디, 비밀번호 검증
        if(findUser == null) {
//            log.warn("아이디 없음. userId={}", userId);
            throw new UserNotFoundException("없는 ID입니다.");
        }
        if(!findUser.getUserPassword().equals(userPassword)) {
//            log.warn("비밀번호 틀림. userId={}", userId);
            throw new UserNotFoundException("비밀전호가 잘못되었습니다.");
        }
//        log.info("로그인 성공: userId={}", userId);
        return findUser;
    }

}
