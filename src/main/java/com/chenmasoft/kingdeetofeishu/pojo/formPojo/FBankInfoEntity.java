package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * FBankInfoEntity
 */
@NoArgsConstructor
@Data
public class FBankInfoEntity {
    /**
     * fBankCountry
     */
    @JSONField(name = "FBankCountry",ordinal = 0)
    private HashMap fBankCountry;
    /**
     * fBankCode
     */
    @JSONField(name = "FBankCode",ordinal = 2)
    private String fBankCode;
    /**
     * fBankHolder
     */
    @JSONField(name = "FBankHolder",ordinal = 4)
    private String fBankHolder;
    /**
     * fOpenBankName
     */
    @JSONField(name = "FOpenBankName",ordinal = 5)
    private String fOpenBankName;
    /**
     * fBankIsDefault
     */
    @JSONField(name = "FBankIsDefault",ordinal = 7)
    private Boolean fBankIsDefault;
}
