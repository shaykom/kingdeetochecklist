package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * FEntityEntity
 */
@NoArgsConstructor
@Data
public class ExpREntity {
    /**
     * fExpID
     */
    @JSONField(name = "FExpID",ordinal = 0)
    private HashMap fExpID;
    /**
     * fTaxSubmitAmt
     */
    @JSONField(name = "FTaxSubmitAmt",ordinal = 1)
    private Integer fTaxSubmitAmt;
    /**
     * fExpenseDeptEntryID
     */
    @JSONField(name = "FExpenseDeptEntryID",ordinal = 2)
    private HashMap fExpenseDeptEntryID;
}
