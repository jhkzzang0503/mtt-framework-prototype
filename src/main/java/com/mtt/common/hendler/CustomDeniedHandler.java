package com.mtt.common.hendler;

import com.mtt.common.exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.mtt.error.enums.ErrorCode;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author :
 * @name :
 * <PRE>
 * </PRE>
 * @class : CustomDeniedHandler
 * @date : 24. 7. 1.
 * @history :
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 * 1
 * </PRE>
 */
@Component
public class CustomDeniedHandler implements AccessDeniedHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws CustomException, IOException {
        ErrorCode errorCode = ErrorCode.RESULT_MSG_401;

        //throw new CustomException(errorCode);
        // JSON 형식으로 에러 메시지를 클라이언트에게 전송
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\"errorCode\":\"" + errorCode.getErrorCd() + "\", \"message\": \"" + errorCode.getErrorMsg() + "\"}");
        writer.flush();
        writer.close();
    }
}
