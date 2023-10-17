package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class CheckListEntity {
    /**
     * fMaterialId
     */
    @JSONField(name = "FMaterialId",ordinal = 1)
    private HashMap fMaterialId;
    /**
     * fUnitID
     */
    @JSONField(name = "FUnitID",ordinal = 5)
    private HashMap fUnitID;
    /**
     * fBaseUnitId
     */
    @JSONField(name = "FBaseUnitId",ordinal = 6)
    private HashMap fBaseUnitId;
    /**
     * fInspectQty
     */
    @JSONField(name = "FInspectQty",ordinal = 10)
    private BigDecimal fInspectQty;
    /**
     * fQualifiedQty
     */
    @JSONField(name = "FQualifiedQty",ordinal = 20)
    private BigDecimal fQualifiedQty;
    /**
     * fUnqualifiedQty
     */
    @JSONField(name = "FUnqualifiedQty",ordinal = 30)
    private BigDecimal fUnqualifiedQty;
    /**
     * fInspectResult
     */
    @JSONField(name = "FInspectResult",ordinal = 40)
    private String fInspectResult;
    /**
     * F_MS2
     */
    @JSONField(name = "F_MS2",ordinal = 41)
    private String F_MS2;
    /**
     * F_JH
     */
    @JSONField(name = "F_JH",ordinal = 42)
    private String F_JH;
    /**
     * F_TXM
     */
    @JSONField(name = "F_TXM",ordinal = 43)
    private String F_TXM;
    /**
     * F_WTMS
     */
    @JSONField(name = "F_WTMS",ordinal = 44)
    private String F_WTMS;
    /**
     * fSupplierId
     */
    @JSONField(name = "FSupplierId",ordinal = 45)
    private HashMap fSupplierId;
    /**
     * FSrcBillType0
     */
    @JSONField(name = "FSrcBillType0",ordinal = 46)
    private String FSrcBillType0;
    /**
     * fSrcBillNo0
     */
    @JSONField(name = "FSrcBillNo0",ordinal = 47)
    private String fSrcBillNo0;
    /**
     * FSrcInterId0
     */
    @JSONField(name = "FSrcInterId0",ordinal = 48)
    private int FSrcInterId0;
    /**
     * FSrcEntryId0
     */
    @JSONField(name = "FSrcEntryId0",ordinal = 49)
    private int FSrcEntryId0;
    /**
     * fPolicyDetail
     */
    @JSONField(name = "FPolicyDetail",ordinal = 50)
    private PolicyDetail[] fPolicyDetail;
//    /**
//     * FReferDetail
//     */
//    @JSONField(name = "FReferDetail",ordinal = 60)
//    private ReferDetail[] FReferDetail;
    /**
     * FEntity_Link
     */
    @JSONField(name = "FEntity_Link",ordinal = 70)
    private LinkDetail[] FEntity_Link;
}
