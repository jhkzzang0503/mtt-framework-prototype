package com.mtt.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @name :
 * <PRE>
 *     에러응답 dto
 * </PRE>
 * @author : 배유리(qodbfl9560@mteletec.com)
 * @class  : ErrorRes
 * @date   : 2024/07/12
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1 2024/07/12  17:00      배유리(qodbfl9560@mteletec.com)     최초작성
 * </PRE>
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorRes {
    private final HttpStatus status;
    private final int code;
    private final String message;

    public static ResponseEntity<ErrorRes> error(CustomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorRes.builder()
                        .status(e.getErrorCode().getHttpStatus())
                        .code(e.getErrorCode().getErrorCd())
                        .message(e.getErrorCode().getErrorMsg())
                        .build());

    }
}
