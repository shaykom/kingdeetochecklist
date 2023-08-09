package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
public class Supplier {
    /**
     * fCreateOrgId
     */
    @JSONField(name = "FCreateOrgId",ordinal = 0)
    private HashMap fCreateOrgId;
    /**
     * fUseOrgId
     */
    @JSONField(name = "FUseOrgId",ordinal = 3)
    private HashMap fUseOrgId;
    /**
     * fName
     */
    @JSONField(name = "FName",ordinal = 5)
    private String fName;
//     "FGroup": {
//        "FNumber": ""
//    },

    @JSONField(name = "FGroup",ordinal = 5)
    private HashMap FGroup;
    /**
     * fBankInfo
     */
    @JSONField(name = "FBankInfo",ordinal = 8)
    private List<FBankInfoEntity> fBankInfo;
}
