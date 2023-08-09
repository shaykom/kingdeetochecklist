package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * FEntityEntity
 */
@NoArgsConstructor
@Data
public class FOtherPayAbleEntity {
    /**
     * fEntryID
     */
//    @JSONField(name = "FEntryID")
//    private Integer fEntryID;
    /**
     * fCOSTID
     */
    @JSONField(name = "FCOSTID",ordinal = 0)
    private HashMap fCOSTID;
    /**
     * fCOSTDEPARTMENTID
     */
    @JSONField(name = "FCOSTDEPARTMENTID",ordinal = 10)
    private HashMap fCOSTDEPARTMENTID;
    /**
     * fINVOICETYPE
     */
    @JSONField(name = "FINVOICETYPE",ordinal = 20)
    private String fINVOICETYPE;
    /**
     * fEntryTaxRate
     */
    @JSONField(name = "FEntryTaxRate",ordinal = 30)
    private Integer fEntryTaxRate;
    /**
     * fNOTAXAMOUNTFOR
     */
    @JSONField(name = "FNOTAXAMOUNTFOR",ordinal = 40)
    private BigDecimal fNOTAXAMOUNTFOR;
    /**
     * fTAXAMOUNTFOR
     */
//    @JSONField(name = "FTAXAMOUNTFOR",ordinal = 50)
//    private Integer fTAXAMOUNTFOR;
    /**
     * fTOTALAMOUNTFOR
     */
//    @JSONField(name = "FTOTALAMOUNTFOR")
//    private Integer fTOTALAMOUNTFOR;
    /**
     * fNOTSETTLEAMOUNTFOR_D
     */
//    @JSONField(name = "FNOTSETTLEAMOUNTFOR_D")
//    private Integer fNOTSETTLEAMOUNTFOR_D;
    /**
     * fNOTAXAMOUNT_D
     */
//    @JSONField(name = "FNOTAXAMOUNT_D")
//    private Integer fNOTAXAMOUNT_D;
    /**
     * fTAXAMOUNT_D
     */
//    @JSONField(name = "FTAXAMOUNT_D")
//    private Integer fTAXAMOUNT_D;
    /**
     * fCOMMENT
     */
    @JSONField(name = "FCOMMENT",ordinal = 50)
    private String fCOMMENT;
    /**
     * fSourceBillNo
     */
//    @JSONField(name = "FSourceBillNo")
//    private String fSourceBillNo;
//    /**
//     * fCREATEINVOICE
//     */
//    @JSONField(name = "FCREATEINVOICE")
//    private boolean fCREATEINVOICE;
//    /**
//     * fIVAmountFor
//     */
//    @JSONField(name = "FIVAmountFor")
//    private Integer fIVAmountFor;
//    /**
//     * fPaySubEntity
//     */
//    @JSONField(name = "FPaySubEntity")
//    private List<HashMap> fPaySubEntity;
}
