package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * FEntityDetailEntity
 */
@NoArgsConstructor
@Data
public class PayableEntityDetail {
    /**
     * fCOSTID
     */
    @JSONField(name = "FCOSTID",ordinal = 0)
    private HashMap fCOSTID;
    /**
     * fPriceQty
     */
    @JSONField(name = "FPriceQty",ordinal = 1)
    private BigDecimal fPriceQty;
    @JSONField(name = "F_TSLY_Text",ordinal = 6)
    private  String F_TSLY_Text;
    /**
     * fTaxPrice
     */
    @JSONField(name = "FTaxPrice",ordinal = 2)
    private BigDecimal fTaxPrice;
    @JSONField(name = "FCOSTDEPARTMENTID",ordinal = 20)
    private HashMap FCOSTDEPARTMENTID;
}
