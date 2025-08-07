package com.mtt.common.comm.repository;

import com.mtt.common.comm.mapper.CommMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @name :
 * <PRE>
 *     CommRepository의 설명
 * </PRE>
 * @author : 장호근(jhkwebm@mteletec.com)
 * @class  : CommRepository
 * @date   : 24. 7. 3.
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1 24. 7. 3.  오후 5:51      장호근(jhkwebm@mteletec.com)     최초작성
 * </PRE>
 */

@Repository
@RequiredArgsConstructor
public class CommRepository {

    private final CommMapper commMapper;

    /*public List<CmmCdDto> getCmmCdList(CmmCdDto cmmCdDto) {
        return commMapper.getCmmCdList(cmmCdDto);
    }*/

}
