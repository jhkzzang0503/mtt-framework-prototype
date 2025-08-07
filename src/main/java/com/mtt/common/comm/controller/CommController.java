package com.mtt.common.comm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @name :
 * <PRE>
 *     공통모듈 Controller
 * </PRE>
 * @author : 장호근(jhkwebm@mteletec.com)
 * @class  : CommController
 * @date   : 24. 7. 3.
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1 24. 7. 3.  오후 5:45      장호근(jhkwebm@mteletec.com)     최초작성
 * </PRE>
 */

@Controller
@RequiredArgsConstructor
public class CommController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;


}
