package com.mtt.admin.dto;

import lombok.Data;

@Data
public class AdminDto {
    private String cmmCd;           // 공통코드
    private String cmmCdNm;         // 공통코드명
    private String cmmCdGrpId;      // 공통코드그룹ID
    private String cmmCdGrpNm;      // 공통코드그룹명
    private String useYn;           // 공통코드사용여부
    private String sortOrdNo;       // 정렬순서번호
}
