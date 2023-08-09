package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * FPAYBILLENTRYEntity
 */
@NoArgsConstructor
@Data
public class PayBillEntity {
    /**
     * fSETTLETYPEID 结算方式
     */
    @JSONField(name = "FSETTLETYPEID",ordinal = 0)
    private HashMap fSETTLETYPEID;
    /**
     * fPURPOSEID 付款用途
     */
    @JSONField(name = "FPURPOSEID",ordinal = 1)
    private HashMap fPURPOSEID;
    /**
     * fPAYTOTALAMOUNTFOR 应付金额
     */
    @JSONField(name = "FPAYTOTALAMOUNTFOR",ordinal = 2)
    private BigDecimal fPAYTOTALAMOUNTFOR;
    /**
     * fACCOUNTID 我方银行账号
     */
    @JSONField(name = "FACCOUNTID",ordinal = 3)
    private HashMap fACCOUNTID;

    @JSONField(name = "FCOMMENT",ordinal = 8)
    private  String FCOMMENT;
}
