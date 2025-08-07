package com.mtt.common.comm.dto;

import lombok.Data;
import java.util.List;

@Data
public class CmmCdDto {
    private String cmmCd;           // 공통코드
    private String cmmCdNm;         // 공통코드명
    private String cmmCdGrpId;      // 공통코드그룹ID
    private String cmmCdGrpNm;      // 공통코드그룹명
    private String useYn;           // 공통코드사용여부
    private String sortOrdNo;       // 정렬순서번호

    private List<CmmCdDto> list;    // 입력 값을 list로 담기 위한 컬럼
    
    private String cdDataVlueCntn1; //코드데이터값내용1
    private String cdDataVlueCntn2; //코드데이터값내용2
    private String cdDataVlueCntn3; //코드데이터값내용3
    private String cdDataVlueCntn4; //코드데이터값내용4
    private String cdDataVlueCntn5; //코드데이터값내용5
    private String[] cmmCdArr;

    private String[] selectArr = new String[]{""};
    private boolean selected = false;
    private String targetId;
}
