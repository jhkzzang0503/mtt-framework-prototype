package com.mtt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	// /templates 파일 읽기
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")	// 1. /를 시작으로 하는 모든 요청을 다룬다.
				.addResourceLocations("classpath:/static/")	// 2. 1번에 해당하는 요청을 처리할 자원을 찾을 위치
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));	// 3. 요청에 대한 Cache를 10분으로 설정

		/*registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:/static/css/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));

		registry.addResourceHandler("/fonts/**")
				.addResourceLocations("classpath:/static/fonts/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));

		registry.addResourceHandler("/images/**")
				.addResourceLocations("classpath:/static/images/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
		
		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/static/js/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));*/


	}
}
