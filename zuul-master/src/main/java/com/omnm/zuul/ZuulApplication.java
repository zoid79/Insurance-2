package com.omnm.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import Filter.AuthFilter;
import Filter.PostFilter;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableZuulProxy
public class ZuulApplication extends SpringBootServletInitializer { //implements WebMvcConfigurer

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ZuulApplication.class);
	}
	
//	// 2021.01.08 zuul cors 설정 시도
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
//		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	
	@Bean
    public AuthFilter preFilter() {
        return new AuthFilter();
    }
	
	@Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
	
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//	// 2021.01.08 zuul cors 설정 시도 // do not additionaly use this
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) { // do not additionaly use this
//		// registry.addMapping("/**").allowedOrigins("http://localhost:18080");
//		// registry.addMapping("/**").allowedOrigins("*"); // => not avaliable
//		registry.addMapping("/**"); // 이게 모든 주소, 모든 방식에 대해, 전역 CORS 허용 설정
//	}

}
