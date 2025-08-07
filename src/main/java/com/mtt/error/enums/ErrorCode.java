package com.mtt.error.enums;

import org.springframework.http.HttpStatus;

/**
 * @author : 장호근(jhkwebm@mteletec.com)
 * @name :
 * <PRE>
 * </PRE>
 * @class : ErrorCode
 * @date : 24. 7. 1.
 * @history :
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 * 1 24. 7. 1.  오후 1:16   장호근(jhkwebm@mteletec.com)   최초작성
 * </PRE>
 */
public enum ErrorCode {
    RESULT_MSG_400(400, HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),
    RESULT_MSG_401(401, HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
    RESULT_MSG_403(403, HttpStatus.FORBIDDEN, "웹 페이지를 볼 수 있는 권한이 없습니다."),
    RESULT_MSG_404(404, HttpStatus.NOT_FOUND, "경로를 찾을 수 없습니다."),
    RESULT_MSG_409(409, HttpStatus.CONFLICT, "동일한 사업자가 이미 등록되어 있습니다.\r\n관리자에게 문의해주세요."),
    RESULT_MSG_500(500, HttpStatus.INTERNAL_SERVER_ERROR, "서버에 에러가 발생하였습니다."),

    FILE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다."),
    FILE_READ_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "파일을 읽는 중 에러가 발생하였습니다."),
    FILE_ENCODING_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "파일명 인코딩에 실패하였습니다."),
    FILE_UPLOAD_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드를 실패하였습니다."),
    FILE_DELETE_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "파일 삭제를 실패하였습니다."),

    RPRT_YEAR_RANGE(409, HttpStatus.CONFLICT, "최근 3개년 파일만 업로드 가능합니다."),
    RPRT_MISSING_FIELD(409, HttpStatus.CONFLICT, "전자신고 파일에 필수 값이 누락되었습니다."),
    RPRT_ITEM_NOT_FOUND(404, HttpStatus.NOT_FOUND, "서식 항목을 찾을 수 없습니다."),
    RPRT_NO_EXTRACTABLE_ITEM(400, HttpStatus.BAD_REQUEST, "추출 가능한 항목이 없습니다."),
    RPRT_FILE_FETCH_FAILED(404, HttpStatus.NOT_FOUND, "업로드된 파일을 조회할 수 없습니다."),
    RPRT_INCORRECT_BZNO(400, HttpStatus.BAD_REQUEST, "선택한 고객사와 전자신고 파일의 고객사가 일치하지 않습니다."),
    RPRT_INVALID_FORMAT(400, HttpStatus.BAD_REQUEST, "업로드된 전자신고 파일의 양식이 다릅니다."),
    RPRT_FILE_READ_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "전자신고파일을 읽는 중 오류가 발생했습니다."),
    RPRT_ENC_FILE(400, HttpStatus.NOT_FOUND, "암호화된 전자신고 파일입니다."),

    RPRT_CREATE_ERROR(800, HttpStatus.INTERNAL_SERVER_ERROR, "생성되지 않은 보고서입니다."),
    ;

    private HttpStatus httpStatus;
    private int errorCd;
    private String errorMsg;

    ErrorCode(int errorCd, HttpStatus httpsStatus, String errorMsg) {
        this.httpStatus = httpsStatus;
        this.errorCd = errorCd;
        this.errorMsg = errorMsg;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public int getErrorCd() {
        return this.errorCd;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public static ErrorCode getErrorCode(int errorCd){
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getErrorCd() == errorCd){
                return errorCode;
            }
        }
        // 정의되지 않은 에러는 전부 500에러
        return RESULT_MSG_500;
    }
}
