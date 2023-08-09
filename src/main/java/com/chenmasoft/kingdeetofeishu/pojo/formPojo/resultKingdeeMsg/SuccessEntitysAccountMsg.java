package com.chenmasoft.kingdeetofeishu.pojo.formPojo.resultKingdeeMsg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SuccessEntitysAccountMsg
 */
@NoArgsConstructor
@Data
public class SuccessEntitysAccountMsg {
    /**
     * id
     */
    @JSONField(name = "Id")
    private String id;
    /**
     * number
     */
    @JSONField(name = "Number")
    private String number;
    /**
     * dIndex
     */
    @JSONField(name = "DIndex")
    private Integer dIndex;
}
