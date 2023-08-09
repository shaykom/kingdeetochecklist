package com.chenmasoft.kingdeetofeishu.pojo.formPojo.resultKingdeeMsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorsAccountMsg
 */
@NoArgsConstructor
@Data
public class ErrorsAccountMsg {
    /**
     * fieldName
     */
    @JSONField(name = "FieldName")
    private String fieldName;
    /**
     * message
     */
    @JSONField(name = "Message")
    private String message;
    /**
     * dIndex
     */
    @JSONField(name = "DIndex")
    private Integer dIndex;
}
