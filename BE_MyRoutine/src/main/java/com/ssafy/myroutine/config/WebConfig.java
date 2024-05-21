package com.ssafy.myroutine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 자원 설정
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	// CORS 에러 전역 처리
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
	//	registry.addMapping("/**").allowedOrigins("http://localhost:5173").allowedMethods("POST", "GET", "PUT", "DELETE").allowCredentials(true);
//	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:5173") // 여기에 클라이언트의 도메인을 추가
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(true);
    }

	// 인터셉터 등 처리 가능
//	@Autowired
//	private JwtInterceptor jwtInterceptor;
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/api-user/**",
//				"/swagger-ui/**", "/v3/api-docs/**");
//	}
}