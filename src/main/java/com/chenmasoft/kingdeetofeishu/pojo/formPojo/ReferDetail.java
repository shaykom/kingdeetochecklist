package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ReferDetail {
    /**
     * FSrcBillType
     */
    @JSONField(name = "FSrcBillType",ordinal = 0)
    private String FSrcBillType;
    /**
     * FSrcInterId
     */
    @JSONField(name = "FSrcInterId",ordinal = 1)
    private int FSrcInterId;
    /**
     * FSrcEntryId
     */
    @JSONField(name = "FSrcEntryId",ordinal = 2)
    private int FSrcEntryId;
    /**
     * FEntity_Link_FSId
     */

    public ReferDetail(String FSrcBillType, int FSrcInterId, int FSrcEntryId) {
        this.FSrcBillType = FSrcBillType;
        this.FSrcInterId = FSrcInterId;
        this.FSrcEntryId = FSrcEntryId;
    }
}
