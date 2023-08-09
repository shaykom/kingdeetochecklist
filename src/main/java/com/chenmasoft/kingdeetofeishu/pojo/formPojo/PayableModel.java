package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * ModelEntityXX
 */
@NoArgsConstructor
@Data
public class PayableModel {
    /**
     * fID
     */
    @JSONField(name = "FID",ordinal = 0)
    private Integer fID;
    /**
     * fBillTypeID
     */
    @JSONField(name = "FBillTypeID",ordinal = 1)
    private HashMap fBillTypeID;
    /**
     * fDATE
     */
    @JSONField(name = "FDATE",ordinal = 2)
    private String fDATE;
    /**
     * fENDDATE_H
     */
    @JSONField(name = "FENDDATE_H",ordinal = 3)
    private String fENDDATE_H;
    /**
     * fDOCUMENTSTATUS
     */
//    @JSONField(name = "FDOCUMENTSTATUS",ordinal = 4)
//    private String fDOCUMENTSTATUS;
    /**
     * fSUPPLIERID
     */
    @JSONField(name = "FSUPPLIERID",ordinal = 5)
    private HashMap fSUPPLIERID;
    /**
     * fCURRENCYID
     */
    @JSONField(name = "FCURRENCYID",ordinal = 6)
    private HashMap fCURRENCYID;
    /**
     * fPayConditon
     */
    @JSONField(name = "FPayConditon",ordinal = 7)
    private HashMap fPayConditon;
    /**
     * fBUSINESSTYPE
     */
    @JSONField(name = "FBUSINESSTYPE",ordinal = 8)
    private String fBUSINESSTYPE;
    /**
     * fSETTLEORGID
     */
    @JSONField(name = "FSETTLEORGID",ordinal = 9)
    private HashMap fSETTLEORGID;
    /**
     * fPAYORGID
     */
    @JSONField(name = "FPAYORGID",ordinal = 10)
    private HashMap fPAYORGID;
    @JSONField(name = "FPURCHASEDEPTID",ordinal = 10)
    private HashMap FPURCHASEDEPTID;


    @JSONField(name = "FAP_Remark",ordinal = 10)
    private String FAP_Remark;
    /**
     * fSetAccountType
     */
    @JSONField(name = "FSetAccountType",ordinal = 11)
    private String fSetAccountType;
    /**
     * fsubHeadFinc
     */
    @JSONField(name = "FsubHeadFinc",ordinal = 12)
    private PayableFincEntity fsubHeadFinc;
    /**
     * fEntityDetail
     */
    @JSONField(name = "FEntityDetail",ordinal = 13)
    private List<PayableEntityDetail> fEntityDetail;
}
