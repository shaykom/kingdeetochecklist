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
    /**
     * F_SUWI_Qty
     */
    @JSONField(name = "F_SUWI_Qty",ordinal = 2)
    private BigDecimal F_SUWI_Qty;
    /**
     * F_SUWI_Qty1
     */
    @JSONField(name = "F_SUWI_Qty1",ordinal = 3)
    private BigDecimal F_SUWI_Qty1;
    /**
     * F_SUWI_Qty2
     */
    @JSONField(name = "F_SUWI_Qty2",ordinal = 4)
    private BigDecimal F_SUWI_Qty2;
}
