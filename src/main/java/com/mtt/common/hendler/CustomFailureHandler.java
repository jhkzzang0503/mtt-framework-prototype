package com.mtt.common.hendler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @name :
 * <PRE>
 *     로그인 실패시 동작하는 핸들러
 * </PRE>
 * @author :
 * @class  :
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
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * @name         :
     * <PRE>
     *     ID가 존재하는 계정의 로그인 실패시 로그인 실패횟수증가 처리
     * </PRE>
     * @MethodName   : onAuthenticationFailure
     * @Part         :
     * @Author       :
     * @ModifiedDate :
     * @ReturnType   :
     * ${tages}
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        // JSON 형식으로 에러 메시지를 클라이언트에게 전송
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\"message\": \"" + "fail" + "\"}");
        writer.flush();
        writer.close();
    }
}
