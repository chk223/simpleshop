package simpleshop.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * 회원 가입
     */
    @RequestMapping("/regist")
    public String regist(UserForm userForm){
        User newUser = new User(userForm.getUserId(),userForm.getUserPassword(),null);
        User user = userService.regist(newUser);
        if(user ==null) return "이미 존재하는 아이디입니다.";
        else return "회원가입 성공";
    }

    /**
     * 회원정보 조회
     */
    @RequestMapping("/find-user")
    public User findUser(String id) {
        return userService.findUser(id);
    }
    /**
     * 회원정보 수정
     */
    @RequestMapping("/update-userInfo")
    public User updateUser(String id, UserForm userForm) {
        return userService.chaingeUserInfo(id,userForm);
    }
    /**
     * 회원 탈퇴
     */
    @RequestMapping("/withdraw")
    public void withdraw(String id) {
        userService.withdraw(id);
    }
}
