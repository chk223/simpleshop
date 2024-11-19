package simpleshop.shop.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.service.UserService;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class UserViewController {
    private final UserService userService;
    /**
     * 회원 가입
     */
    @GetMapping("/regist")
    public String registForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "addUser";
    }
    @PostMapping("/regist")
    public String regist(@ModelAttribute UserForm userForm){
        log.info("userId={}, userPassword={}, userName= {}",userForm.getUserId(),userForm.getUserPassword(),userForm.getUserName());
        //가입된 아이디가 아니라면
        User newUser = new User(userForm.getUserId(),userForm.getUserPassword(), userForm.getUserName());
        userService.regist(newUser);
        log.info("회원 가입 성공!"); // 나중에 모달로 띄워주기.
        return "redirect:/";
    }
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("LoginForm", new UserForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserForm userForm, HttpSession  session) {
//        log.info("userId={}, userPassword={}",userForm.getUserId(), userForm.getUserPassword());
        User user = userService.login(userForm.getUserId(),userForm.getUserPassword());
        session.setAttribute("user", user);
        log.info("로그인 성공!");
        return "redirect:/";
    }
   @GetMapping("/logout")
    public String logout(HttpSession  session) {
       session.invalidate();
       return "redirect:/login";
   }

}
