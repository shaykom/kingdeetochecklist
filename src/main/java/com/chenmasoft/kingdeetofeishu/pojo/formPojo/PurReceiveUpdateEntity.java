package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class PurReceiveUpdateEntity {
    /**
     * fEntryID
     */
    @JSONField(name = "FEntryID",ordinal = 0)
    private int fEntryID;
    /**
     * fActReceiveQty
     */
    @JSONField(name = "FActReceiveQty",ordinal = 1)
    private BigDecimal fActReceiveQty;
}
