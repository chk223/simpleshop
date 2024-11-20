package simpleshop.shop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import simpleshop.shop.domain.User;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        HttpSession session = request.getSession(false);//기존 세션 가져오기
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        // 회원가입, 로그인 요청은 Interceptor 제외
        if ((uri.equals("/user/login") && (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("POST")))
                || uri.equals("/user/regist")) {
            return true;// 요청 허용 -> 컨트롤러로 이동
        }
        if (user == null) {// 세션에 "user"가 없으면 로그인 페이지로 리다이렉트
            log.info("Interceptor 발동!! 로그인해야함!!!: URI={}, Method={}", uri, method);
            log.info("세션이 없음: 로그인 페이지 리다이렉트했음.");
            response.sendRedirect("/user/login");
            return false; // 요청 차단 -> 컨트롤러로 이동하지 않음
        }
        log.info("세션이 있음! userId={}", user.getUserId());
        return true;// 세션이 있는 경우 요청 허용 -> 컨트롤러로 이동
    }
}