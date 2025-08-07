package com.mtt.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * @author : 장호근(jhkwebm@mteletec.com)
 * @name :
 * <PRE>
 * </PRE>
 * @class : CncLogFilter
 * @date : 24. 9. 6.
 * @history :
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 * 1 24. 9. 6.  오전 8:55   장호근(jhkwebm@mteletec.com)   최초작성
 * </PRE>
 */

@Component
public class CncLogFilter extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final List<String> CNCL_URL = List.of("/menu/M_", "/logout", "/loginProc");
    private static final List<String> LOGOUT_URL = List.of("/logout");
    private static final List<String> LOGIN_URL = List.of("/loginProc");
    private static final List<String> MENU_URL = List.of("/menu/M_");


    /**
    * @name         :
    * <PRE>
    *     doFilterInternal 설명
    * </PRE>
    * @MethodName   : doFilter
    * @Part         : RichDivineCRM
    * @Author       : 장호근(jhkwebm@mteletec.com)
    * @ModifiedDate : 24. 9. 6. 오전 8:57
    * @ReturnType   :
    * ${tages}
    */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        /*String uri = req.getRequestURI();
        CncLogDto cncLogDto = new CncLogDto();
        cncLogDto.setCncUrl(uri);
        cncLogDto.setBeforeUrl(req.getHeader("Referer"));
        cncLogDto.setSysLginIpad(ClientUtils.getRemoteIP(req));

        Object session  = SecurityContextHolder.getContext().getAuthentication();
        if(ClientUtils.checkUrl(CNCL_URL, uri) && session != null){
            if(ClientUtils.checkUrl(LOGIN_URL, uri)){

                // 통합비밀번호 로그인 시 이력 제외
                if(!commonPwd.equals(req.getParameter("password"))) {
                    cncLogDto.setUsername(req.getParameter("username"));
                    if (ClientUtils.checkUrl(List.of(new String[]{"/msg"}), cncLogDto.getBeforeUrl())) {
                        cncLogDto.setSysAccsDivsCd("tokenLogin");
                    } else {
                        cncLogDto.setSysAccsDivsCd("login");
                    }

                    try {
                        commService.insertCncl(cncLogDto);
                    } catch (Exception e) {
                        LOGGER.debug("cncl ::: {}", e.getMessage());
                    }
                }
            }else{
                Object obj  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserDto user = null;
                if (obj instanceof CustomUserDetails) {
                    user = ((CustomUserDetails) obj).getUser();

                    // 통합 비밀번호 로그인 이력 제외
                    if(!"Y".equals(Objects.requireNonNull(user).getIntgLgin())){
                        cncLogDto.setUserNo(String.valueOf(user.getUserNo()));
                        if(ClientUtils.checkUrl(MENU_URL, uri)){
                            String menuId = uri.substring(uri.lastIndexOf("/") + 1);
                            cncLogDto.setMenuId(menuId);
                            cncLogDto.setSysAccsDivsCd("menu");
                        }

                        if(ClientUtils.checkUrl(LOGOUT_URL, uri)){
                            cncLogDto.setSysAccsDivsCd("logout");
                        }

                        try {
                            commService.insertCncl(cncLogDto);
                        }catch (Exception e){
                            LOGGER.debug("cncl ::: {}", e.getMessage());
                        }
                    }
                }
            }
        }*/

        filterChain.doFilter(req, res);
    }
}
