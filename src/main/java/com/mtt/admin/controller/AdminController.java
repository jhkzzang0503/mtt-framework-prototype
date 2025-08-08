package com.mtt.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtt.admin.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    private final List<String> allowedCommands = Arrays.asList("mvn clean install", "npm install", "npm run dev", "mvn spring-boot:run");

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

    @GetMapping("/admin/getTest")
    public AdminDto getTest(@RequestParam(required = false) String req) {

        AdminDto result = new AdminDto();
        try {
            result.setMessage("asd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @GetMapping("/admin/getLog")
    public List<String> getLog() throws IOException {
        List<String> logs = new ArrayList<>();
        Path logFilePath = Paths.get("logs/spring.log"); // 로그 파일 경로 (추후 설정 파일에서 읽어오도록 수정 필요)
        if (Files.exists(logFilePath)) {
            Files.lines(logFilePath).forEach(logs::add);
        } else {
            logs.add("로그 파일이 존재하지 않습니다.");
        }
        return logs;
    }

    @PostMapping("/admin/executeCommand")
    public String executeCommand(@RequestBody Map<String, String> req) throws IOException {
        String command = req.get("command");

        if (!allowedCommands.contains(command)) {
            return "허용되지 않은 명령어입니다.";
        }

        ProcessBuilder pb = new ProcessBuilder(command.split(" "));
        pb.directory(new File(".")); // 현재 프로젝트 루트 디렉토리 설정
        pb.redirectErrorStream(true); // 표준 에러를 표준 출력으로 리다이렉트

        Process process = pb.start();
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            result.append(e.getMessage());
        }
        process.destroy(); // 프로세스 종료
        return result.toString();
    }
}
