package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * ModelEntity
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpRModel {
    /**
     * fID
     */
    @JSONField(name = "FID",ordinal = 0)
    private Integer fID;
    /**
     * fDate
     */
    @JSONField(name = "FDate",ordinal = 1)
    private String fDate;
    /**
     * fCurrencyID
     */
    @JSONField(name = "FCurrencyID",ordinal = 2)
    private HashMap fCurrencyID;
    /**
     * fOrgID
     */
    @JSONField(name = "FOrgID",ordinal = 3)
    private HashMap fOrgID;
    /**
     * fCausa
     */
    @JSONField(name = "FCausa",ordinal = 4)
    private String fCausa;
    /**
     * fProposerID
     */
    @JSONField(name = "FProposerID",ordinal = 5)
    private HashMap fProposerID;
    /**
     * fRequestDeptID
     */
    @JSONField(name = "FRequestDeptID",ordinal = 6)
    private HashMap fRequestDeptID;
    /**
     * fBillTypeID
     */
    @JSONField(name = "FBillTypeID",ordinal = 7)
    private HashMap fBillTypeID;
    /**
     * fExpenseOrgId
     */
    @JSONField(name = "FExpenseOrgId",ordinal = 8)
    private HashMap fExpenseOrgId;
    /**
     * fExpenseDeptID
     */
    @JSONField(name = "FExpenseDeptID",ordinal = 9)
    private HashMap fExpenseDeptID;
    /**
     * fCONTACTUNITTYPE
     */
    @JSONField(name = "FCONTACTUNITTYPE",ordinal = 10)
    private String fCONTACTUNITTYPE;
    /**
     * fCONTACTUNIT
     */
    @JSONField(name = "FCONTACTUNIT",ordinal = 11)
    private HashMap fCONTACTUNIT;
    /**
     * fPayOrgId
     */
    @JSONField(name = "FPayOrgId",ordinal = 12)
    private HashMap fPayOrgId;
    /**
     * fPaySettlleTypeID
     */
    @JSONField(name = "FPaySettlleTypeID",ordinal = 13)
    private HashMap fPaySettlleTypeID;
    /**
     * fBankBranchT
     */
    @JSONField(name = "FBankBranchT",ordinal = 14)
    private String fBankBranchT;
    /**
     * fBankAccountNameT
     */
    @JSONField(name = "FBankAccountNameT",ordinal = 15)
    private String fBankAccountNameT;
    /**
     * fBankAccountT
     */
    @JSONField(name = "FBankAccountT",ordinal = 16)
    private String fBankAccountT;
    /**
     * fLocCurrencyID
     */
    @JSONField(name = "FLocCurrencyID",ordinal = 17)
    private HashMap fLocCurrencyID;
    /**
     * fExchangeTypeID
     */
    @JSONField(name = "FExchangeTypeID",ordinal = 18)
    private HashMap fExchangeTypeID;
    /**
     * fExchangeRate
     */
    @JSONField(name = "FExchangeRate",ordinal = 19)
    private Integer fExchangeRate;
    /**
     * fEntity
     */
    @JSONField(name = "FEntity",ordinal = 20)
    private List<ExpREntity> fEntity;
}
