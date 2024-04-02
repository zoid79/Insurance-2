package com.ict.zuul.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry.addMapping("/**").allowedOrigins("http://localhost:18080");
		// registry.addMapping("/**").allowedOrigins("*"); // => not avaliable
		registry.addMapping("/**"); // 이게 모든 주소, 모든 방식에 대해, 전역 CORS 허용 설정
		registry.addMapping("*").allowCredentials(true);
	} // zuul is basically open for cors, but at least set one cors setting
	// and then kill the duplicate
	// zuul.ignored-headers=Access-Control-Allow-Credentials, Access-Control-Allow-Origin
	// must be with this, in applicatin.properties
	
}
