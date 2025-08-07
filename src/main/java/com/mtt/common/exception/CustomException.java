package com.mtt.common.exception;

import com.mtt.error.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @name :
 * <PRE>
 *     Custom Exception
 * </PRE>
 * @author :
 * @class  : CustomException
 * @date   :
 *
 * @history
 * <PRE>
 * No  Date        time       Author                             Desc
 * --- ----------- ---------- ---------------------------------- -----
 *   1
 * </PRE>
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
}
