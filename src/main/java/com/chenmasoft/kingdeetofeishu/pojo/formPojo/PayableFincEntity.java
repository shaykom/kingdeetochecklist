package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * FsubHeadFincEntity
 */
@NoArgsConstructor
@Data
public class PayableFincEntity {
    /**
     * fACCNTTIMEJUDGETIME
     */
    @JSONField(name = "FACCNTTIMEJUDGETIME",ordinal = 0)
    private String fACCNTTIMEJUDGETIME;
    /**
     * fMAINBOOKSTDCURRID
     */
    @JSONField(name = "FMAINBOOKSTDCURRID",ordinal = 10)
    private HashMap fMAINBOOKSTDCURRID;
    /**
     * fEXCHANGETYPE
     */
    @JSONField(name = "FEXCHANGETYPE",ordinal = 20)
    private HashMap fEXCHANGETYPE;
    /**
     * fExchangeRate
     */
    @JSONField(name = "FExchangeRate",ordinal = 30)
    private Integer fExchangeRate;
    /**
     * fNoTaxAmountFor
     */

}
