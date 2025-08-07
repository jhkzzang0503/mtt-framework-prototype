package com.mtt.error;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author : 장호근(jhkwebm@mteletec.com)
 * @name :
 * <PRE>
 * </PRE>
 * @class : CustomError
 * @date : 24. 7. 1.
 * @history :
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 * 1 24. 7. 1.  오후 1:08   장호근(jhkwebm@mteletec.com)   최초작성
 * </PRE>
 */
// 임시 error 처리
@Controller
@RequiredArgsConstructor
public class CustomErrorController {

    @GetMapping("/error/403")
    public String getError(Model model) {
        return "error/403";
    }

    @PostMapping("/error/403")
    public String postError(Model model) {
        return "error/403";
    }
}
