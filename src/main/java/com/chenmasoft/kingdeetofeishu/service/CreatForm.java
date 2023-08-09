package com.chenmasoft.kingdeetofeishu.service;
import java.math.BigDecimal;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuformentry;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformentryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CreatForm {
    @Autowired
    private FishuformService fishuformService;
    @Autowired
    private FishuformentryService fishuformentryService;
    @Autowired
    private SelectForm selectForm;
    public  Form payAble(Fishuform fishuform){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       String instanc= fishuform.getInstanceCode();
        QueryWrapper<Fishuformentry> fishuformentryQueryWrapper =new QueryWrapper<Fishuformentry>()
                .eq("instance_code",instanc);
        //飞书明细信息
        List<Fishuformentry> fishuformentries= fishuformentryService.list(fishuformentryQueryWrapper);


        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        PayableModel payableModel=new PayableModel();
        List<PayableEntityDetail> payableEntityDetails=   new ArrayList<PayableEntityDetail>();
        PayableFincEntity payableFincEntity =new PayableFincEntity();

        payableFincEntity.setFACCNTTIMEJUDGETIME(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime()))));
        payableFincEntity.setFMAINBOOKSTDCURRID(new HashMap<String, String>() {{
            put("FNUMBER", "PRE001");
        }});
        payableFincEntity.setFEXCHANGETYPE(new HashMap<String, String>() {{
            put("FNUMBER", "HLTX01_SYS");
        }});
   //     payableFincEntity.setFExchangeRate(1);


        form.setFormid("AP_Payable");
        form.setData(dataEntity);



         dataEntity.setNeedUpDateFields(new ArrayList());
         dataEntity.setNeedReturnFields( new ArrayList());
         dataEntity.setIsDeleteEntry("true");
         dataEntity.setSubSystemId("");
         dataEntity.setIsVerifyBaseDataField("false");
         dataEntity.setIsEntryBatchFill("true");
         dataEntity.setValidateFlag("true");
         dataEntity.setNumberSearch("true");
         dataEntity.setIsAutoAdjustField("false");
         dataEntity.setInterationFlags("");
         dataEntity.setIgnoreInterationFlag("");
         dataEntity.setModel(payableModel);

       // payableModel.setFID(0);
        payableModel.setFBillTypeID(new HashMap() {{
            put("FNUMBER", "YFD02_SYS"); //费用应付类型应付单
        }});
        payableModel.setFDATE(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime()))));
        payableModel.setFENDDATE_H(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime()))));
        // payableModel.setFDOCUMENTSTATUS("");


        payableModel.setFSUPPLIERID(new HashMap() {{
            put("Fnumber", fishuform.getAccountNameNumber()); //供应商id
        }});


        payableModel.setFCURRENCYID(new HashMap<String, String>() {{
            put("FNUMBER", fishuform.getCurrencyFnumber());
        }});
        payableModel.setFPayConditon(new HashMap<String, String>() {{
            put("FNUMBER", "PRE001");
        }});
        payableModel.setFBUSINESSTYPE("FY");
        payableModel.setFSETTLEORGID(new HashMap<String, String>() {{
            put("FNUMBER", fishuform.getPayAccountFnumber());
        }});

        payableModel.setFPAYORGID(new HashMap<String, String>() {{
            put("FNUMBER", fishuform.getPayAccountFnumber());
        }});
        payableModel.setFPURCHASEDEPTID(new HashMap<String, String>() {{
            put("FNUMBER", fishuform.getKingdeeDepartment());
        }});
        payableModel.setFSetAccountType("");
        payableModel.setFsubHeadFinc(payableFincEntity);
        payableModel.setFEntityDetail(payableEntityDetails);
         payableModel.setFAP_Remark(fishuform.getFremark());


        for (Fishuformentry fishuformentry:
        fishuformentries) {
            PayableEntityDetail payableEntityDetail=new PayableEntityDetail();
            payableEntityDetail.setFCOSTID(new HashMap<String, String>() {{
                put("Fnumber", fishuformentry.getMonyTypeNumber());//费用类型
            }});
            payableEntityDetail.setFPriceQty(new BigDecimal("1"));
            payableEntityDetail.setFTaxPrice(new BigDecimal(fishuformentry.getMoney()));
            payableEntityDetail.setFCOSTDEPARTMENTID(new HashMap<String, String>() {{
                put("Fnumber", fishuform.getKingdeeDepartment());//费用部门
            }});
          //  payableEntityDetail.setF_TSLY_Text(fishuformentry.getSomething());
            payableEntityDetails.add(payableEntityDetail);
        }


return form;

    }

    public  Form payBill(Fishuform fishuform){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String instanc= fishuform.getInstanceCode();
        QueryWrapper<Fishuformentry> fishuformentryQueryWrapper =new QueryWrapper<Fishuformentry>()
                .eq("instance_code",instanc);
        //飞书明细信息
        List<Fishuformentry> fishuformentries= fishuformentryService.list(fishuformentryQueryWrapper);


        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        PayBillModel payBillModel = new PayBillModel();

        List<PayBillEntity> payBillEntities = new ArrayList<>();


        form.setFormid("AP_PAYBILL");
        form.setData(dataEntity);



        dataEntity.setNeedUpDateFields(new ArrayList());
        dataEntity.setNeedReturnFields( new ArrayList());
        dataEntity.setIsDeleteEntry("true");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(payBillModel);


      //  payBillModel.setFID(0);
        payBillModel.setFBillTypeID(new HashMap<String, String>() {{
            put("FNumber", "FKDLX01_SYS");
        }});//单据类型FKDLX01_SYS 采购业务付款单
        payBillModel.setFDATE(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime()))));
        payBillModel.setFCONTACTUNITTYPE("BD_Supplier");

        payBillModel.setFCONTACTUNIT(new HashMap<String, String>() {{
            put("FNumber", fishuform.getAccountNameNumber()); //往来单位
        }});
        payBillModel.setFRECTUNITTYPE("BD_Supplier");
        payBillModel.setFRECTUNIT(new HashMap<String, String>() {{
            put("FNumber", fishuform.getAccountNameNumber());//收款单位
        }});
        payBillModel.setFCURRENCYID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getCurrencyFnumber());
        }});
        payBillModel.setFSETTLEORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});
        payBillModel.setFPURCHASEORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});
        payBillModel.setFPURCHASEDEPTID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getKingdeeDepartment());
        }});
        payBillModel.setFDOCUMENTSTATUS("");
        payBillModel.setFPAYORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});
        payBillModel.setFSETTLECUR(new HashMap<String, String>() {{
            put("FNumber", fishuform.getCurrencyFnumber());//币别
        }});
        payBillModel.setFSETTLEMAINBOOKID(new HashMap<String, String>() {{
            put("FNumber", "PRE001");
        }});



        payBillModel.setFREMARK(fishuform.getFremark());



        payBillModel.setFPAYBILLENTRY(payBillEntities);

        for (Fishuformentry fishuformentry:
                fishuformentries) {
            PayBillEntity payBillEntity = new PayBillEntity();
            payBillEntity.setFSETTLETYPEID(new HashMap<String, String>() {{
                put("FNumber", "JSFS04_SYS");
            }}); //JSFS04_SYS 电汇结算方式
            payBillEntity.setFPURPOSEID(new HashMap<String, String>() {{
                put("FNumber", "SFKYT09_SYS");
            }});//SFKYT09_SYS 预付款
            payBillEntity.setFPAYTOTALAMOUNTFOR(new BigDecimal(fishuformentry.getMoney()));
            payBillEntity.setFACCOUNTID(new HashMap<String, String>() {{
                put("FNumber", "");//我方银行账号
            }});
            //payBillEntity.setFCOMMENT(fishuformentry.getSomething());

            payBillEntities.add( payBillEntity);
        }


       return form;
    }


    public  Form otherPayAble(Fishuform fishuform){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String instanc= fishuform.getInstanceCode();
        QueryWrapper<Fishuformentry> fishuformentryQueryWrapper =new QueryWrapper<Fishuformentry>()
                .eq("instance_code",instanc);
        //飞书明细信息
        List<Fishuformentry> fishuformentries= fishuformentryService.list(fishuformentryQueryWrapper);


        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
       OtherPayableModel otherPayableModel=new OtherPayableModel();

        List<FOtherPayAbleEntity> fOtherPayAbleEntityList = new ArrayList<>();


        form.setFormid("AP_OtherPayable");
        form.setData(dataEntity);



        dataEntity.setNeedUpDateFields(new ArrayList());
        dataEntity.setNeedReturnFields( new ArrayList());
        dataEntity.setIsDeleteEntry("true");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(otherPayableModel);

        otherPayableModel.setFBillTypeID(new HashMap<String, String>() {{
            put("FNumber", "QTYFD01_SYS");
        }});//  单据类型：报销的其他应付单QTYFD01_SYS
        otherPayableModel.setFDATE(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime())))); //单据业务日期
        otherPayableModel.setFCONTACTUNITTYPE("BD_Customer");  //往来单位类型   // 客户 BD_Customer   员工 BD_Empinfo

        otherPayableModel.setFCONTACTUNIT(new HashMap<String, String>() {{
            put("FNumber", fishuform.getAccountNameNumber());
        }});  //  往来单位id
        otherPayableModel.setFCURRENCYID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getCurrencyFnumber());
        }});//  币别
        otherPayableModel.setFSETTLEORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});//    结算组织
        otherPayableModel.setFDEPARTMENTID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getKingdeeDepartment());
        }});
        otherPayableModel.setFPURCHASEORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});//     采购组织
        otherPayableModel.setFPAYORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());
        }});//     付款组织
        otherPayableModel.setFPURCHASEDEPTID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getKingdeeDepartment());
        }});//   采购部门
        otherPayableModel.setFPURCHASERGROUPID(new HashMap<String, String>() {{
            put("FNumber", "");
        }});//  采购组
        otherPayableModel.setFPURCHASERID(new HashMap<String, String>() {{
            put("FNumber", "");
        }});//
        otherPayableModel.setFRemarks("");//
        otherPayableModel.setFMAINBOOKSTDCURRID(new HashMap<String, String>() {{
            put("FNumber", "PRE001");
        }});// 币别
        otherPayableModel.setFEXCHANGETYPE(new HashMap<String, String>() {{
            put("FNumber", "HLTX01_SYS");
        }});// 汇率类型
       // otherPayableModel.setFExchangeRate(new BigDecimal(1));//  税率
        otherPayableModel.setFRemarks(fishuform.getFremark());
        otherPayableModel.setFEntity(fOtherPayAbleEntityList);//


        for (Fishuformentry fishuformentry:
        fishuformentries) {

            FOtherPayAbleEntity fOtherPayAbleEntity = new FOtherPayAbleEntity();

          //  fOtherPayAbleEntity.setFEntryTaxRate(0);//税率

            fOtherPayAbleEntity.setFCOSTDEPARTMENTID(new HashMap<String, String>() {{
                put("Fnumber", fishuform.getKingdeeDepartment());//费用部门
            }});//费用承担部门

            fOtherPayAbleEntity.setFCOSTID(new HashMap<String, String>() {{
                put("Fnumber", fishuformentry.getMonyTypeNumber());
            }});//费用项目编码

            fOtherPayAbleEntity.setFINVOICETYPE(fishuformentry.getFapiaoNumber());//发票类型

          //  fOtherPayAbleEntity.setFCOMMENT(fishuformentry.getSomething());
try {
    fOtherPayAbleEntity.setFNOTAXAMOUNTFOR(new BigDecimal(fishuformentry.getMoney()));// 不含税金额
}catch (Exception e){
    log.info("com.chenmasoft.kingdeetofeishu.service.CreatForm.otherPayAble(CreatForm.java:339)"+e.getMessage()+e.getStackTrace());
}

            fOtherPayAbleEntityList.add( fOtherPayAbleEntity);
        }



        return form;
    }
    public  Form payBillTwo(Fishuform fishuform){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String instanc= fishuform.getInstanceCode();
        QueryWrapper<Fishuformentry> fishuformentryQueryWrapper =new QueryWrapper<Fishuformentry>()
                .eq("instance_code",instanc);
        //飞书明细信息
        List<Fishuformentry> fishuformentries= fishuformentryService.list(fishuformentryQueryWrapper);


        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        PayBillModel payBillModel = new PayBillModel();

        List<PayBillEntity> payBillEntities = new ArrayList<>();


        form.setFormid("AP_PAYBILL");
        form.setData(dataEntity);



        dataEntity.setNeedUpDateFields(new ArrayList());
        dataEntity.setNeedReturnFields( new ArrayList());
        dataEntity.setIsDeleteEntry("true");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(payBillModel);


        payBillModel.setFBillTypeID(new HashMap<String, String>() {{
            put("FNumber", "FKDLX02_SYS");
        }});
        payBillModel.setFDATE(simpleDateFormat.format(new Date(Long.valueOf(fishuform.getEndTime()))));
        payBillModel.setFCONTACTUNITTYPE("BD_Empinfo");

        payBillModel.setFCONTACTUNIT(new HashMap<String, String>() {{
            put("FNumber", fishuform.getAccountNameNumber());//往来单位的id,根据飞书单据中获取
        }});
        payBillModel.setFRECTUNITTYPE("BD_Empinfo");
        payBillModel.setFRECTUNIT(new HashMap<String, String>() {{
            put("FNumber", fishuform.getAccountNameNumber());//收款单位的id，根据飞书单据中获取
        }});
        payBillModel.setFCURRENCYID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getCurrencyFnumber());//币别
        }});
        payBillModel.setFSETTLEORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());//结算组织
        }});
        payBillModel.setFPURCHASEORGID(new HashMap<String, String>() {{
            put("FNumber", "");
        }});
        payBillModel.setFDOCUMENTSTATUS("Z");
        payBillModel.setFPAYORGID(new HashMap<String, String>() {{
            put("FNumber", fishuform.getPayAccountFnumber());//付款组织
        }});
        payBillModel.setFSETTLECUR(new HashMap<String, String>() {{
            put("FNumber", fishuform.getCurrencyFnumber());
        }});
        payBillModel.setFSETTLEMAINBOOKID(new HashMap<String, String>() {{
            put("FNumber", "PRE001");
        }});
        payBillModel.setFREMARK(fishuform.getFremark());
        payBillModel.setFPAYBILLENTRY(payBillEntities);


        for (Fishuformentry fishuformentry:
                fishuformentries) {
            PayBillEntity payBillEntity = new PayBillEntity();
            payBillEntity.setFSETTLETYPEID(new HashMap<String, String>() {{
                put("FNumber", "JSFS04_SYS");
            }});//   (电汇)结算方式
            payBillEntity.setFPURPOSEID(new HashMap<String, String>() {{
                put("FNumber", "SFKYT10_SYS");
            }});//付款用途
            payBillEntity.setFACCOUNTID(new HashMap<String, String>() {{
                put("FNumber", "");
            }});//我方银行账号（不填，暂存付款单）

         //   payBillEntity.setFCOMMENT(fishuformentry.getSomething());

            payBillEntity.setFPAYTOTALAMOUNTFOR(new BigDecimal(fishuformentry.getMoney()));//表体-应付金额


            payBillEntities.add( payBillEntity);
        }


        return form;
    }

}
