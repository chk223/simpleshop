package simpleshop.shop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import simpleshop.shop.domain.User;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("user")
    public User addUserSession(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
