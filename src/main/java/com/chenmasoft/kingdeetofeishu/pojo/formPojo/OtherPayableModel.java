package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * otherPayableModel
 */
@NoArgsConstructor
@Data
public class OtherPayableModel {

    /**
     * fBillTypeID
     */
    @JSONField(name = "FBillTypeID",ordinal = 0)
    private HashMap fBillTypeID;


    /**
     * fDATE
     */
    @JSONField(name = "FDATE",ordinal = 1)
    private String fDATE;


    /**
     * fCONTACTUNITTYPE
     */
    @JSONField(name = "FCONTACTUNITTYPE",ordinal = 4)
    private String fCONTACTUNITTYPE;
    /**
     * fCONTACTUNIT
     */
    @JSONField(name = "FCONTACTUNIT",ordinal = 9)
    private HashMap fCONTACTUNIT;
    /**
     * fCURRENCYID
     */
    @JSONField(name = "FCURRENCYID",ordinal = 11)
    private HashMap fCURRENCYID;



    /**
     * fDEPARTMENTID
     */
    @JSONField(name = "FDEPARTMENTID")
    private HashMap fDEPARTMENTID;
    /**
     * fSETTLEORGID
     */
    @JSONField(name = "FSETTLEORGID",ordinal = 20)
    private HashMap fSETTLEORGID;
    /**
     * fPURCHASEORGID
     */
    @JSONField(name = "FPURCHASEORGID",ordinal = 30)
    private HashMap fPURCHASEORGID;
    /**
     * fPAYORGID
     */
    @JSONField(name = "FPAYORGID",ordinal = 40)
    private HashMap fPAYORGID;
    /**
     * fPURCHASEDEPTID
     */
    @JSONField(name = "FPURCHASEDEPTID",ordinal = 50)
    private HashMap fPURCHASEDEPTID;
    /**
     * fPURCHASERGROUPID
     */
    @JSONField(name = "FPURCHASERGROUPID",ordinal = 60)
    private HashMap fPURCHASERGROUPID;
    /**
     * fPURCHASERID
     */
    @JSONField(name = "FPURCHASERID",ordinal = 70)
    private HashMap fPURCHASERID;
    /**
     * fRemarks
     */
    @JSONField(name = "FRemarks",ordinal = 80)
    private String fRemarks;
    /**
     * fScanPoint
     */
//    @JSONField(name = "FScanPoint")
//    private HashMap fScanPoint;
    /**
     * fSettleTypeID
     */
//    @JSONField(name = "FSettleTypeID")
//    private HashMap fSettleTypeID;
    /**
     * fMAINBOOKSTDCURRID
     */
    @JSONField(name = "FMAINBOOKSTDCURRID",ordinal = 90)
    private HashMap fMAINBOOKSTDCURRID;
    /**
     * fEXCHANGETYPE
     */
    @JSONField(name = "FEXCHANGETYPE",ordinal = 100)
    private HashMap fEXCHANGETYPE;
    /**
     * fExchangeRate
     */
    @JSONField(name = "FExchangeRate",ordinal = 110)
    private BigDecimal fExchangeRate;
    /**
     * fNOTAXAMOUNT
     */
//    @JSONField(name = "FNOTAXAMOUNT")
//    private Integer fNOTAXAMOUNT;
    /**
     * fTAXAMOUNT
     */
//    @JSONField(name = "FTAXAMOUNT")
//    private Integer fTAXAMOUNT;
    /**
     * fACCNTTIMEJUDGETIME
     */
//    @JSONField(name = "FACCNTTIMEJUDGETIME")
//    private String fACCNTTIMEJUDGETIME;
    /**
     * fCancelStatus
     */
//    @JSONField(name = "FCancelStatus")
//    private String fCancelStatus;
    /**
     * fBUSINESSTYPE
     */
//    @JSONField(name = "FBUSINESSTYPE")
//    private String fBUSINESSTYPE;
    /**
     * fHisLoanBalanceFor
     */
//    @JSONField(name = "FHisLoanBalanceFor")
//    private Integer fHisLoanBalanceFor;
    /**
     * fPRESETBASE1
     */
//    @JSONField(name = "FPRESETBASE1")
//    private HashMap fPRESETBASE1;
//    /**
//     * fPRESETBASE2
//     */
//    @JSONField(name = "FPRESETBASE2")
//    private HashMap fPRESETBASE2;
//    /**
//     * fPRESETASSISTANT1
//     */
//    @JSONField(name = "FPRESETASSISTANT1")
//    private HashMap fPRESETASSISTANT1;
//    /**
//     * fPRESETASSISTANT2
//     */
//    @JSONField(name = "FPRESETASSISTANT2")
//    private HashMap fPRESETASSISTANT2;
//    /**
//     * fPRESETTEXT1
//     */
//    @JSONField(name = "FPRESETTEXT1")
//    private String fPRESETTEXT1;
//    /**
//     * fPRESETTEXT2
//     */
//    @JSONField(name = "FPRESETTEXT2")
//    private String fPRESETTEXT2;
    /**
     * fEntity
     */
    @JSONField(name = "FEntity",ordinal = 120)
    private List<FOtherPayAbleEntity> fEntity;
}
