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
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.RESULT_MSG_403;

        throw new CustomException(errorCode);
    }
}
