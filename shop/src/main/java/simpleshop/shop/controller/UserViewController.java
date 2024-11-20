package simpleshop.shop.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simpleshop.shop.domain.LoginForm;
import simpleshop.shop.domain.User;
import simpleshop.shop.domain.UserForm;
import simpleshop.shop.exception.user.UserNotFoundException;
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
    public String regist(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult,  HttpSession session){        if (bindingResult.hasErrors()) {
            return "addUser"; // 검증 실패 시 다시 폼으로 돌아감
        }
//        log.info("userId={}, userPassword={}, userName= {}",userForm.getUserId(),userForm.getUserPassword(),userForm.getUserName());
        //가입된 아이디가 아니라면
        User newUser = new User(userForm.getUserId(),userForm.getUserPassword(), userForm.getUserName());
        userService.regist(newUser);
        session.setAttribute("user",newUser);
//        log.info("회원 가입 성공!"); // 나중에 모달로 띄워주기.
        return "redirect:/";
    }
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, HttpSession  session, HttpServletRequest request, Model model) {
//        log.info("userId={}, userPassword={}",userForm.getUserId(), userForm.getUserPassword());
        if (bindingResult.hasErrors()) {
//            log.info("컨트롤러에서 로그인 시도: userId={}, userPw={}",loginForm.getUserId(),loginForm.getUserPassword());
            bindingResult.getAllErrors().forEach(error -> log.warn("에러 내용: {}", error.getDefaultMessage()));
            return "login"; // 검증 실패 시 다시 로그인 폼으로 돌아감
        }
        try {
            User user = userService.login(loginForm.getUserId(),loginForm.getUserPassword());
            // 기존 세션 무효화
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //새 세션 생성, user 설정
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", user);
//            log.info("로그인 성공! userId={}", user.getUserId());
            return "redirect:/";
        }
        catch (UserNotFoundException e) {
//            log.info("로그인 실패: " +e.getMessage());
            bindingResult.reject("login.failed", e.getMessage());
            model.addAttribute("loginFailed", e.getMessage());
            return "login";
        }

    }
   @GetMapping("/logout")
    public String logout(HttpSession session) {
       if(session != null) {
           session.invalidate();//세션 무효화
           log.info("로그아웃 완료");
       }
       return "redirect:/login";
   }

}
