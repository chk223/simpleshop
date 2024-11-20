package simpleshop.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import simpleshop.shop.interceptor.LoginInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)// LoginInterceptor 적용
                .addPathPatterns("/**")  // 모든 경로에 적용 -> 완성 후에 인터셉터 적용 범위 설정할 것!
                .excludePathPatterns("/", "/login", "/regist", "/error", "/resources/**", "/static/**", "/favicon.ico", "/css/**", "/js/**", "/images/**");// 제외 경로 설정
    }
}
