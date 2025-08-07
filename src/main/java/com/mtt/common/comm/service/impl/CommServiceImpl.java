package com.mtt.common.comm.service.impl;

import com.mtt.common.comm.service.CommService;
import com.mtt.common.comm.repository.CommRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @name :
 * <PRE>
 *     CommServiceImpl의 설명
 * </PRE>
 * @author : 이름(email)
 * @class  : CommServiceImpl
 * @date   : 24. 7. 3.
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1 24. 7. 3.  오후 5:51      이름(email)     최초작성
 * </PRE>
 */

@Service(value = "commService")
@CacheConfig(cacheNames = "commServiceCache")
@RequiredArgsConstructor
public class CommServiceImpl implements CommService {

    private final CommRepository commRepository;

    /**
     * @name         :
     * <PRE>
     *     설명
     * </PRE>
     * @MethodName   : getCmmCdList
     * @Part         : mtt-framework
     * @Author       : 이름(email)
     * @ModifiedDate : 2025/06/04 10:52
     * @ReturnType   :
     * ${tages}
     */
    /*@Override
    public List<CmmCdDto> getCmmCdList(CmmCdDto cmmCdDto) {
        return commRepository.getCmmCdList(cmmCdDto);
    }*/

}
