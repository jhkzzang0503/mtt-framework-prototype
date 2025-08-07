package com.mtt.config;

import com.mtt.common.filter.CncLogFilter;
import com.mtt.common.filter.CustomExceptionFilter;
import com.mtt.common.hendler.CustomAuthenticationSuccessHandler;
import com.mtt.common.hendler.CustomDeniedHandler;
import com.mtt.common.hendler.CustomFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomFailureHandler customAuthFailureHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomDeniedHandler customDeniedHandler;

    @Autowired
    private CustomExceptionFilter customExceptionFilter;

    @Autowired
    private CncLogFilter cncLogFilter;

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 권한계층화
    /*@Bean
    public RoleHierarchy roleHierarchy() {
        *//*
            RDM_ADM	관리자_본사
            RDM_BLD	관리자_사업단
            RDM_MNG	관리자_지점
            RDM_STF	관리자_스탭
            RDM_SYS	시스템운영자
            RDM_SFP	전문가FP
            RDM_JFP	외부FP
            RDM_TMP	임시권한
            ROLE_ANONYMOUS 미로그인, 로그아웃
         *//*

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy(
                "RDM_ADM > RDM_BLD\n" + "RDM_BLD > RDM_MNG\n" + "RDM_MNG > RDM_STF\n" + "RDM_STF > RDM_SYS\n"
                    + "RDM_SYS > RDM_JFP\n"
                        + "RDM_JFP > RDM_TMP\n" + "RDM_TMP > ROLE_ANONYMOUS");

        return hierarchy;
    }*/

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(cncLogFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customExceptionFilter, CncLogFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/js/**", "/css/**", "/file/**", "/fonts/**", "/images/**", "/libs/**").permitAll()
                ).exceptionHandling(e -> {
                    e.accessDeniedHandler(customDeniedHandler);
                });

        http
                .formLogin((auth) -> auth.loginPage("/") //로그인페이지 url
                        .loginProcessingUrl("/loginProc") //로그인처리 url
                        //.failureHandler(customAuthFailureHandler) // 로그인실패핸들러
                        //.successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                );

        //		http basic 방식 로그인, 접근 권한 필요시 ID,비밀번호 입력창이 alert 이후 헤더에 넣어서 사용
        //		http
        //			.httpBasic(Customizer.withDefaults());

        http
                .logout((auth) -> auth.logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))

                
                         // 권한이 없는상태에서 메뉴 클릭시 로그인 페이지로 가도록 수정
                         // 2024-10-10 차은우
                        .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/login");
                        })
                );

        // csrf 세팅 필요
        http.csrf((auth) -> {
           auth.ignoringRequestMatchers("/**");
        });



        //		<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
        //		csrf 비적용 코드, csrf처리시엔 form 안에 상단 html 코드 삽입 또는 th태그 사용
        //		예) <form action="/joinproc" th:action="@{/joinproc}" method="post">
        //		http
        //			.csrf((auth)->auth.disable());

        //다중 로그인 설정
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)                        //최대 로그인 허용 수
                        .maxSessionsPreventsLogin(true)        //  true: 새로운 로그인 차단 false: 기존 로그인 삭제
                );


        //JSESSIONID 탈취 방지, 인증 성공시 SessionId 변경
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        return http.build();
    }

}