package com.mtt.common.hendler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @name :
 * <PRE>
 *     로그인 성공시 동작하는 핸들러
 * </PRE>
 * @author :
 * @class  : CustomAuthenticationSuccessHandler
 * @date   :
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1
 * </PRE>
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * @name         :
     * <PRE>
     *     로그인 성공시 로그인 실패횟수, 승인 여부등 검증
     * </PRE>
     * @MethodName   : onAuthenticationSuccess
     * @Part         :
     * @Author       :
     * @ModifiedDate :
     * @ReturnType   : void
     * ${tages}
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //성공
        response.getWriter().write("{\"message\":\"ok\"}");
    }
}