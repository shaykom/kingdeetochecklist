package com.chenmasoft.kingdeetofeishu.pojo.formPojo.resultKingdeeMsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ResponseStatusAccountMsg
 */
@NoArgsConstructor
@Data
public class ResponseStatusAccountMsg {
    /**
     * errorCode
     */
    @JSONField(name = "ErrorCode")
    private String errorCode;
    /**
     * isSuccess
     */
    @JSONField(name = "IsSuccess")
    private String isSuccess;
    /**
     * errors
     */
    @JSONField(name = "Errors")
    private List<ErrorsAccountMsg> errors;
    /**
     * successEntitys
     */
    @JSONField(name = "SuccessEntitys")
    private List<SuccessEntitysAccountMsg> successEntitys;
    /**
     * successMessages
     */
    @JSONField(name = "SuccessMessages")
    private List<ErrorsAccountMsg> successMessages;
    /**
     * msgCode
     */
    @JSONField(name = "MsgCode")
    private String msgCode;
}
