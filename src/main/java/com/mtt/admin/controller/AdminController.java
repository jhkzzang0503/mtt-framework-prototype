package com.mtt.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtt.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

@Profile("local")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;

    @Value("${react.root.path}")
    private String reactRoot;

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
    @PostMapping("/admin/postTest")
    public AdminDto postTest(@RequestBody(required = false) Map<String, String> req) throws RuntimeException, IOException {
        AdminDto adminDto = new AdminDto();

        LOGGER.debug("req :: ", req);
        LOGGER.debug("reactRoot :: ", reactRoot);

        Path filePath = Paths.get(reactRoot, req.get("filePath"), req.get("fileName"));

        Files.writeString(filePath, req.get("fileContent").toString());
        LOGGER.info("File created successfully at: {}", filePath);

        adminDto.setCode("0");
        adminDto.setMessage(String.format("%s 파일 생성 완료.", filePath));
        adminDto.setStatus("success");

        return adminDto;
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
    @GetMapping("/admin/getTest")
    public AdminDto getTest(@RequestParam(required = false) String req){

        AdminDto result = new AdminDto();
        try{
            result.setMessage("asd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



}
