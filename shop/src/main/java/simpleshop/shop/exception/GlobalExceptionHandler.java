package simpleshop.shop.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public String handleUnauthorizedException(ResponseStatusException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:/login"; // 로그인 페이지로 이동
    }
}