package com.mtt.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtt.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @name :
 * <PRE>
 *     Admin Controller
 * </PRE>
 * @author : 장호근(jhkwebm@mteletec.com)
 * @class  : AdminController
 * @date   :
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1 25. 8. 7.  오전 10:06      장호근(jhkwebm@mteletec.com)     최초작성
 * </PRE>
 */

@Profile("dev")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;

    /**
    * @name         : 테스트 조회
    * <PRE>
    *
    * </PRE>
    * @MethodName   : getTest
    * @Part         : mtt
    * @Author       :
    * @ModifiedDate :
    * @ReturnType   :
    * ${tages}
    */
    @PostMapping("/admin/test.do")
    public AdminDto getTest(@RequestBody(required = false) Map<String, String> req, Model model){

        AdminDto result = new AdminDto();
        result.setCmmCd("asd");

        return result;
    }

    /**
     * @name         : 테스트 조회
     * <PRE>
     *
     * </PRE>
     * @MethodName   : getTest
     * @Part         : mtt
     * @Author       :
     * @ModifiedDate :
     * @ReturnType   :
     * ${tages}
     */
    @GetMapping("/admin/test.do")
    public AdminDto getTest(@RequestParam(required = false) String req, Model model){

        AdminDto result = new AdminDto();
        result.setCmmCd("asd");

        return result;
    }



}
