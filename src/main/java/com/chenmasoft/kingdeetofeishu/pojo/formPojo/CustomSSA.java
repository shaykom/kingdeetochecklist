package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class CustomSSA {
    /**
     * fCUSTID
     */
    @JSONField(name = "FCUSTID")
    private Integer fCUSTID;
    /**
     * fCreateOrgId
     */
    @JSONField(name = "FCreateOrgId")
    private HashMap fCreateOrgId;
    /**
     * fNumber
     */
    @JSONField(name = "FNumber")
    private String fNumber;
    /**
     * fUseOrgId
     */
    @JSONField(name = "FUseOrgId")
    private HashMap fUseOrgId;
    /**
     * fName
     */
    @JSONField(name = "FName")
    private String fName;
    /**
     * fGroup
     */
    @JSONField(name = "FGroup")
    private HashMap fGroup;
}
