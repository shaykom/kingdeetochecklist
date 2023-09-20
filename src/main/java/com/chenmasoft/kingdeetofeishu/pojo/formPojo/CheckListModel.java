package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
public class CheckListModel {
    /**
     * fid
     */
    @JSONField(name = "FID",ordinal = 0)
    private int fid;
    /**
     * fBillTypeID
     */
    @JSONField(name = "FBillTypeID",ordinal = 1)
    private HashMap fBillTypeID;
    /**
     * fBusinessType
     */
    @JSONField(name = "FBusinessType",ordinal = 2)
    private String fBusinessType;

    /**
     * fDATE
     */
    @JSONField(name = "FDATE",ordinal = 3)
    private String fDATE;


    /**
     * fSourceOrgId
     */
    @JSONField(name = "FSourceOrgId",ordinal = 4)
    private HashMap fSourceOrgId;
    /**
     * fInspectOrgId
     */
    @JSONField(name = "FInspectOrgId",ordinal = 5)
    private HashMap fInspectOrgId;
    /**
     * F_BH2
     */
    @JSONField(name = "F_BH2",ordinal = 6)
    private String F_BH2;
    /**
     * fDescription
     */
    @JSONField(name = "FDescription",ordinal = 7)
    private String fDescription;
    /**
     * F_BPJY
     */
    @JSONField(name = "F_BPJY",ordinal = 8)
    private Boolean F_BPJY;
    /**
     * F_CPJY
     */
    @JSONField(name = "F_CPJY",ordinal = 9)
    private Boolean F_CPJY;
    /**
     * fInspectorId
     */
    @JSONField(name = "FInspectorId",ordinal = 10)
    private HashMap fInspectorId;
    /**
     * fEntity
     */
    @JSONField(name = "FEntity",ordinal = 11)
    private List<CheckListEntity> fEntity;
}
