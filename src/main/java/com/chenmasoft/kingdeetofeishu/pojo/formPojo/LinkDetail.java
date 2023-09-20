package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class LinkDetail {
    /**
     * FEntity_Link_FRuleId
     */
    @JSONField(name = "FEntity_Link_FRuleId",ordinal = 0)
    private String FEntity_Link_FRuleId;
    /**
     * FEntity_Link_FSTableName
     */
    @JSONField(name = "FEntity_Link_FSTableName",ordinal = 1)
    private String FEntity_Link_FSTableName;
    /**
     * FEntity_Link_FSBillId
     */
    @JSONField(name = "FEntity_Link_FSBillId",ordinal = 2)
    private int FEntity_Link_FSBillId;
    /**
     * FEntity_Link_FSId
     */
    @JSONField(name = "FEntity_Link_FSId",ordinal = 3)
    private int FEntity_Link_FSId;
    /**
     * FEntity_Link_FBaseInspectQtyOld
     */
    @JSONField(name = "FEntity_Link_FBaseInspectQtyOld",ordinal = 4)
    private BigDecimal FEntity_Link_FBaseInspectQtyOld;
    /**
     * FEntity_Link_FBaseInspectQty
     */
    @JSONField(name = "FEntity_Link_FBaseInspectQty",ordinal = 5)
    private BigDecimal FEntity_Link_FBaseInspectQty;

    public LinkDetail(String FEntity_Link_FRuleId, String FEntity_Link_FSTableName, int FEntity_Link_FSBillId, int FEntity_Link_FSId, BigDecimal FEntity_Link_FBaseInspectQtyOld, BigDecimal FEntity_Link_FBaseInspectQty) {
        this.FEntity_Link_FRuleId = FEntity_Link_FRuleId;
        this.FEntity_Link_FSTableName = FEntity_Link_FSTableName;
        this.FEntity_Link_FSBillId = FEntity_Link_FSBillId;
        this.FEntity_Link_FSId = FEntity_Link_FSId;
        this.FEntity_Link_FBaseInspectQtyOld = FEntity_Link_FBaseInspectQtyOld;
        this.FEntity_Link_FBaseInspectQty = FEntity_Link_FBaseInspectQty;
    }
}
