package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class BarCodeModel {
    /**
     * fBarCode
     */
    @JSONField(name = "FBarCode",ordinal = 0)
    private String fBarCode;
    /**
     * fBarCodeRule
     */
    @JSONField(name = "FBarCodeRule",ordinal = 1)
    private HashMap fBarCodeRule;
    /**
     * fMaterialId
     */
    @JSONField(name = "FMaterialId",ordinal = 2)
    private HashMap fMaterialId;
    /**
     * fMaterialName
     */
    @JSONField(name = "FMaterialName",ordinal = 3)
    private String fMaterialName;
    /**
     * fMaterialModel
     */
    @JSONField(name = "FMaterialModel",ordinal = 4)
    private String fMaterialModel;
    /**
     * fAuxPropId
     */
    @JSONField(name = "FAuxPropId",ordinal = 5)
    private int fAuxPropId;
    /**
     * fLot
     */
    @JSONField(name = "FLot",ordinal = 6)
    private HashMap fLot;
    /**
     * fAuxiliaryUnitId
     */
    @JSONField(name = "FAuxiliaryUnitId",ordinal = 7)
    private HashMap fAuxiliaryUnitId;
    /**
     * fAuxiliaryQty
     */
    @JSONField(name = "FAuxiliaryQty",ordinal = 8)
    private BigDecimal fAuxiliaryQty;
    /**
     * fBaseUnitId
     */
    @JSONField(name = "FBaseUnitId",ordinal = 9)
    private HashMap fBaseUnitId;
    /**
     * fBaseQty
     */
    @JSONField(name = "FBaseQty",ordinal = 10)
    private BigDecimal fBaseQty;
    /**
     * fQty
     */
    @JSONField(name = "FQty",ordinal = 11)
    private BigDecimal fQty;
    /**
     * fSupplierId
     */
    @JSONField(name = "FSupplierId",ordinal = 12)
    private HashMap fSupplierId;
    /**
     * F_JH
     */
    @JSONField(name = "F_JH",ordinal = 13)
    private String F_JH;
}
