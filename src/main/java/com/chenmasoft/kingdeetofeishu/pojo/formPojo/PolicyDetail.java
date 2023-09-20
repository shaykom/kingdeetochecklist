package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class PolicyDetail {
    /**
     * fPolicyMaterialId
     */
    @JSONField(name = "FPolicyMaterialId",ordinal = 1)
    private HashMap fPolicyMaterialId;
    /**
     * fPolicyStatus
     */
    @JSONField(name = "FPolicyStatus",ordinal = 10)
    private String fPolicyStatus;
    /**
     * fPolicyQty
     */
    @JSONField(name = "FPolicyQty",ordinal = 20)
    private BigDecimal fPolicyQty;
    /**
     * fBasePolicyQty
     */
    @JSONField(name = "FBasePolicyQty",ordinal = 30)
    private BigDecimal fBasePolicyQty;
    /**
     * fUsePolicy
     */
    @JSONField(name = "FUsePolicy",ordinal = 40)
    private String fUsePolicy;

    public PolicyDetail(HashMap fPolicyMaterialId, String fPolicyStatus, BigDecimal fPolicyQty, BigDecimal fBasePolicyQty, String fUsePolicy) {
        this.fPolicyMaterialId = fPolicyMaterialId;
        this.fPolicyStatus = fPolicyStatus;
        this.fPolicyQty = fPolicyQty;
        this.fBasePolicyQty = fBasePolicyQty;
        this.fUsePolicy = fUsePolicy;
    }
}
