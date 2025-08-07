package com.mtt.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtt.common.exception.CustomException;
import com.mtt.common.exception.ErrorRes;
import com.mtt.error.enums.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ResourceLoader;

/**
 * @author :
 * @name :
 * <PRE>
 * </PRE>
 * @class : customFilter
 * @date :
 * @history :
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 * 1 24. 8. 5.
 * </PRE>
 */
@Component
public class CustomExceptionFilter implements Filter {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
    * @name         :
    * <PRE>
    *     필터 초기화
    * </PRE>
    * @MethodName   : init
    * @Part         :
    * @Author       :
    * @ModifiedDate :
    * @ReturnType   :
    * ${tages}
    */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
    * @name         :
    * <PRE>
    *     커스텀 필터 처리
    * </PRE>
    * @MethodName   : doFilter
    * @Part         :
    * @Author       :
    * @ModifiedDate :
    * @ReturnType   :
    * ${tages}
    */
    @Override
    // public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws CustomException, IOException, ServletException {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        filterChain.doFilter(req, res);
        /*try {
            filterChain.doFilter(req, res);
        }catch (Exception e){
            *//*LOGGER.error(e.getMessage());
            Throwable cause = e.getCause();
            if(cause instanceof CustomException){
                handleActionException(req, res, (CustomException) cause);
            }else{
                handleActionException(req, res, new CustomException(ErrorCode.getErrorCode(res.getStatus())));
            }*//*
        }*/
    }

    /**
    * @name         :
    * <PRE>
    *     필터 정리
    * </PRE>
    * @MethodName   : destroy
    * @Part         : RichDivineCRM
    * @Author       : 장호근(jhkwebm@mteletec.com)
    * @ModifiedDate : 24. 8. 5. 오전 8:41
    * @ReturnType   :
    * ${tages}
    */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
    * @name         : action url Exception 처리 핸들러
    * <PRE>
    *     handleActionException의 설명
    * </PRE>
    * @MethodName   : handleActionException
    * @Part         : RichDivineCRM
    * @Author       : 장호근(jhkwebm@mteletec.com)
    * @ModifiedDate : 24. 8. 7. 오전 9:51
    * @ReturnType   :
    * ${tages}
    */
    private void handleActionException(HttpServletRequest request, HttpServletResponse response, CustomException ex) throws IOException {
        ErrorRes entityBody = ErrorRes.error(ex).getBody();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(entityBody.getStatus().value());

        String entityBodyToJson = objectMapper.writeValueAsString(entityBody);

        try (PrintWriter writer = response.getWriter()) {
            writer.write(entityBodyToJson);
            writer.flush();
        }
    }

    /**
    * @name         : 화면 url Exception 처리 핸들러
    * <PRE>
    *     handlePageException의 설명
    * </PRE>
    * @MethodName   : handlePageException
    * @Part         : RichDivineCRM
    * @Author       : 장호근(jhkwebm@mteletec.com)
    * @ModifiedDate : 24. 8. 7. 오전 9:51
    * @ReturnType   :
    * ${tages}
    */
    private void handlePageException(HttpServletRequest request, HttpServletResponse response, CustomException ex) throws IOException {
        ErrorRes entityBody = ErrorRes.error(ex).getBody();

        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(entityBody.getStatus().value());

        Resource resource = resourceLoader.getResource("classpath:static/error/index.html");

        StringBuilder errorHtml = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                errorHtml.append(line).append("\n");
            }
        }

        try (PrintWriter writer = response.getWriter()) {
            writer.write(errorHtml.toString()
                    .replace("{{errorCode}}", String.valueOf(entityBody.getStatus().value()))
                    .replace("{{errorMsg}}", entityBody.getMessage())
            );

            writer.flush();
        }
    }
}
