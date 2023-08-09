package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * ModelEntityX
 */
@NoArgsConstructor
@Data
public class PayBillModel  {
    /**
     * fID
     */
    @JSONField(name = "FID",ordinal = 0)
    private Integer fID;
    /**
     * fBillTypeID 单据类型
     */
    @JSONField(name = "FBillTypeID",ordinal = 1)
    private HashMap fBillTypeID;
    /**
     * fDATE 单据日期
     */
    @JSONField(name = "FDATE",ordinal = 2)
    private String fDATE;
    /**
     * fCONTACTUNITTYPE 往来单位类型
     */
    @JSONField(name = "FCONTACTUNITTYPE",ordinal = 3)
    private String fCONTACTUNITTYPE;
    /**
     * fCONTACTUNIT 往来单位
     */
    @JSONField(name = "FCONTACTUNIT",ordinal = 4)
    private HashMap fCONTACTUNIT;
    /**
     * fRECTUNITTYPE 收款单位类型
     */
    @JSONField(name = "FRECTUNITTYPE",ordinal = 5)
    private String fRECTUNITTYPE;
    /**
     * fRECTUNIT 收款单位
     */
    @JSONField(name = "FRECTUNIT",ordinal = 6)
    private HashMap fRECTUNIT;
    /**
     * fCURRENCYID 币别
     */
    @JSONField(name = "FCURRENCYID",ordinal = 7)
    private HashMap fCURRENCYID;
    /**
     * fSETTLEORGID  结算组织
     */
    @JSONField(name = "FSETTLEORGID",ordinal = 8)
    private HashMap fSETTLEORGID;
    /**
     * fPURCHASEORGID 采购组织
     */
    @JSONField(name = "FPURCHASEORGID",ordinal = 9)
    private HashMap fPURCHASEORGID;

    //采购部门
    @JSONField(name = "FPURCHASEDEPTID",ordinal = 9)
    private HashMap FPURCHASEDEPTID;


    /**
     * fDOCUMENTSTATUS 单据状态
     */
    @JSONField(name = "FDOCUMENTSTATUS",ordinal = 10)
    private String fDOCUMENTSTATUS;
    /**
     * fPAYORGID  付款组织
     */
    @JSONField(name = "FPAYORGID",ordinal = 11)
    private HashMap fPAYORGID;
    /**
     * fSETTLECUR 结算币别
     */
    @JSONField(name = "FSETTLECUR",ordinal = 12)
    private HashMap fSETTLECUR;
    /**
     * fSETTLEMAINBOOKID 结算币别本位币
     */
    @JSONField(name = "FSETTLEMAINBOOKID",ordinal = 13)
    private HashMap fSETTLEMAINBOOKID;

    @JSONField(name = "FREMARK",ordinal = 13)
    private String FREMARK;
    /**
     * fPAYBILLENTRY 付款明细
     */
    @JSONField(name = "FPAYBILLENTRY",ordinal = 14)
    private List<PayBillEntity> fPAYBILLENTRY;
}
