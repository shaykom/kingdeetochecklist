package com.chenmasoft.kingdeetofeishu.service;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenmasoft.kingdeetofeishu.dao.entity.*;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.*;

import java.text.SimpleDateFormat;
import java.util.*;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformentryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreatForm {
    @Autowired
    private FishuformService fishuformService;
    @Autowired
    private FishuformentryService fishuformentryService;
    @Autowired
    private SelectForm selectForm;
    @Autowired
    private SaveTokingdee saveTokingdee;
    @Autowired
    private KingdeeFormSaveSoso kingdeeFormSaveSoso;
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

    public  Form barCode(BarCodeForm code){
        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        BarCodeModel barCodeModel=new BarCodeModel();

        form.setFormid("BD_BarCodeMainFile");
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
        dataEntity.setModel(barCodeModel);

        barCodeModel.setFBarCode(code.getBarCode()); //条码
        barCodeModel.setFBarCodeRule(new HashMap<String, String>() {{
            put("FNumber", "07");
        }});//  条码规则
        barCodeModel.setFMaterialId(new HashMap<String, String>() {{
            put("FNumber", code.getMaterialNumber() == null ? "" : code.getMaterialNumber());
        }}); //物料编码
        barCodeModel.setFAuxPropId(
                Integer.parseInt(code.getAuxProp())
//                new HashMap<String, BigDecimal>() {{
//                    put("FID", code.getAuxProp() == null ? BigDecimal.valueOf(0) : new BigDecimal(code.getAuxProp()));
//                }}
        );
        barCodeModel.setFLot(new HashMap<String, String>() {{
            put("FNumber", code.getLot() == null ? "" : code.getLot());
        }});
        barCodeModel.setFAuxiliaryUnitId(new HashMap<String, String>() {{
            put("FNumber", code.getAuxUnit() == null ? "" : code.getAuxUnit());
        }});
//        barCodeModel.setFAuxiliaryQty(new BigDecimal(code.getAuxQty()));
        barCodeModel.setFAuxiliaryQty(new BigDecimal(1));
        barCodeModel.setFBaseUnitId(new HashMap<String, String>() {{
            put("FNumber", code.getBaseUnit() == null ? "" : code.getBaseUnit());
        }});
        barCodeModel.setFBaseQty(new BigDecimal(code.getBaseQty()));
        barCodeModel.setFQty(new BigDecimal(code.getBaseQty()));
        barCodeModel.setFSupplierId(new HashMap<String, String>() {{
            put("FNumber", code.getSupplierNumber() == null ? "" : code.getSupplierNumber());
        }});
        barCodeModel.setF_JH(code.getJH());
        return form;
    }

    public Form checkList(CheckList checkListform){
        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        CheckListModel checkListModel=new CheckListModel();

        List<CheckListEntity> checkListEntityList = new ArrayList<>();

        CheckListArray[] checkListArrays = checkListform.getCheckListArray();

        form.setFormid("QM_InspectBill");
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
        dataEntity.setIsAutoSubmitAndAudit("true");
        dataEntity.setModel(checkListModel);
        if(!Objects.equals(checkListform.getFid(), "") && !Objects.equals(checkListform.getFid(), "自动生成")){
            checkListModel.setFid(Integer.parseInt(checkListform.getFid()));
        }
        else {
            checkListModel.setFid(0);
            //dataEntity.setIsAutoSubmitAndAudit("true");
        }
        checkListModel.setFBillTypeID(new HashMap<String, String>() {{
            put("FNumber", "JYD001_SYS");
        }});//  单据类型
        if(Objects.equals(checkListform.getBillTypeNumber(), "SLD03_SYS"))
        {
            checkListModel.setFBusinessType("2");
        }
        else
        {
            checkListModel.setFBusinessType("1");
        }
        checkListModel.setFDATE(checkListform.getCheckDate()); //日期
        checkListModel.setFSourceOrgId(new HashMap<String, String>() {{
            put("FNumber", checkListform.getOrgNumber());
        }});  //  来源组织
        checkListModel.setFInspectOrgId(new HashMap<String, String>() {{
            put("FNumber", checkListform.getOrgNumber());
        }});//  质检组织
        checkListModel.setF_BH2(checkListform.getBagNumber());
        checkListModel.setFDescription(checkListform.getBagNote());
        checkListModel.setF_BPJY(true);
        checkListModel.setFInspectorId(new HashMap<String, String>() {{
            put("FNumber", checkListform.getCheckerNumber());
        }});
        checkListModel.setFEntity(checkListEntityList);//

        BigDecimal allMeter = new BigDecimal(0);

        BigDecimal actReceiveQty = new BigDecimal(checkListform.getActReceiveQty());
        if(Objects.equals(actReceiveQty, new BigDecimal(0)))
        {
            String receiveFEntryId = checkListform.getReceiveFEntryId() == null ? "0" : checkListform.getReceiveFEntryId();
            JSONArray ja = selectForm.selectCheckJoinQty(receiveFEntryId);
            if(ja.size() > 0) {
                JSONArray ja0 = (JSONArray) ja.get(0);
                BigDecimal FCheckBaseQty = ja0.getBigDecimal(0);
                for (CheckListArray checkListArray: checkListArrays) {
                    allMeter = allMeter.add(new BigDecimal(checkListArray.getMeter()));
                }
                actReceiveQty = FCheckBaseQty.add(allMeter);
            }
        }

        for (CheckListArray checkListArray: checkListArrays) {

            CheckListEntity checkListEntity = new CheckListEntity();
            checkListEntity.setFMaterialId(new HashMap<String, String>() {{
                put("Fnumber", checkListform.getMaterialNumber());//
            }});//物料编码
            checkListEntity.setFUnitID(new HashMap<String, String>() {{
                put("Fnumber", checkListform.getUnitNumber());//
            }});//单位
            checkListEntity.setF_MS2(checkListArray.getMeter());
            //allMeter = allMeter.add(new BigDecimal(checkListArray.getMeter()));
            checkListEntity.setF_JH(checkListArray.getSeq());
            checkListEntity.setF_TXM(checkListArray.getBarCode());;

            if(Objects.equals(checkListform.getFid(), "") || Objects.equals(checkListform.getFid(), "自动生成")) {
                BarCodeForm barCodeForm = new BarCodeForm();
                barCodeForm.setMaterialNumber(checkListform.getMaterialNumber());
                barCodeForm.setMaterialName(checkListform.getMaterialName());
                barCodeForm.setSpecification(checkListform.getSpecification());
                barCodeForm.setAuxProp(checkListform.getAuxPropId());
                barCodeForm.setLot(checkListform.getLotNumber());
                barCodeForm.setAuxUnit(checkListform.getExtAuxUnitNumber());
                barCodeForm.setAuxQty(checkListform.getQty6());
                barCodeForm.setBaseUnit(checkListform.getBaseUnitNumber());
                barCodeForm.setBaseQty(checkListArray.getMeter());
                barCodeForm.setSupplierNumber(checkListform.getSupplierNumber());
                barCodeForm.setBarCode(checkListArray.getBarCode());
                barCodeForm.setJH(checkListArray.getSeq());
                saveTokingdee.saveBarCode(barCodeForm);
            }

            checkListEntity.setF_WTMS(checkListArray.getNote());
            checkListEntity.setFSupplierId(new HashMap<String, String>() {{
                put("Fnumber", checkListform.getSupplierNumber());//
            }});
            checkListEntity.setFSrcBillNo0(checkListform.getSourceBillNo());

            String isPass = checkListform.getIsPass();
            if(Objects.equals(isPass, "合格")) {
                checkListEntity.setFInspectQty(new BigDecimal(checkListArray.getMeter()));//检验数量

                checkListEntity.setFQualifiedQty(new BigDecimal(checkListArray.getMeter()));//合格数量

                checkListEntity.setFUnqualifiedQty(BigDecimal.valueOf(0));// 不合格数量

                checkListEntity.setFInspectResult("1");


                PolicyDetail[] policyDetails = {new PolicyDetail(
                        new HashMap<String, String>() {{
                            put("Fnumber", checkListform.getMaterialNumber());//
                        }},"1",new BigDecimal(checkListArray.getMeter()),new BigDecimal(checkListArray.getMeter()),"A"
                )};
                checkListEntity.setFPolicyDetail(policyDetails);
            }
            if(Objects.equals(isPass, "不合格")) {
                checkListEntity.setFInspectQty(new BigDecimal(checkListArray.getMeter()));//检验数量

                checkListEntity.setFQualifiedQty(BigDecimal.valueOf(0));//合格数量

                checkListEntity.setFUnqualifiedQty(new BigDecimal(checkListArray.getMeter()));// 不合格数量

                checkListEntity.setFInspectResult("2");

                PolicyDetail[] policyDetails = {new PolicyDetail(
                        new HashMap<String, String>() {{
                            put("Fnumber", checkListform.getMaterialNumber());//
                        }},"2",new BigDecimal(checkListArray.getMeter()),new BigDecimal(checkListArray.getMeter()),"F"
                )};
                checkListEntity.setFPolicyDetail(policyDetails);
            }
            if(Objects.equals(isPass, "让步接收")) {
                checkListEntity.setFInspectQty(new BigDecimal(checkListArray.getMeter()));//检验数量

                checkListEntity.setFQualifiedQty(BigDecimal.valueOf(0));//合格数量

                checkListEntity.setFUnqualifiedQty(new BigDecimal(checkListArray.getMeter()));// 不合格数量

                checkListEntity.setFInspectResult("2");

                PolicyDetail[] policyDetails = {new PolicyDetail(
                        new HashMap<String, String>() {{
                            put("Fnumber", checkListform.getMaterialNumber());//
                        }},"2",new BigDecimal(checkListArray.getMeter()),new BigDecimal(checkListArray.getMeter()),"B"
                )};
                checkListEntity.setFPolicyDetail(policyDetails);
            }
            LinkDetail[] linkDetails = {new LinkDetail(
                    "QM_PURReceive2Inspect","T_PUR_ReceiveEntry",
                    Integer.parseInt(checkListform.getReceiveFid() == null ? "0" : checkListform.getReceiveFid()),
                    Integer.parseInt(checkListform.getReceiveFEntryId() == null ? "0" : checkListform.getReceiveFEntryId()),
                    actReceiveQty,
                    actReceiveQty
            )};
            checkListEntity.setFEntity_Link(linkDetails);

            checkListEntityList.add(checkListEntity);
        }
        return form;
    }

    public JSONObject purReceive(CheckList checkListform){
//        if(!Objects.equals(checkListform.getFid(), "") && !Objects.equals(checkListform.getFid(), "自动生成")){
////            JSONArray jsonArray = selectForm.selectSourceCheckQty(checkListform.getFid());
////            if(jsonArray.size() > 0)
////            {
////                jsonArray
////            }
//
//            JSONObject joPurReceive = new JSONObject();
//            JSONObject isSuccess = new JSONObject();
//            isSuccess.put("IsSuccess", true);
//            JSONObject responseStatus = new JSONObject();
//            responseStatus.put("ResponseStatus", isSuccess);
//            joPurReceive.put("Result", responseStatus);
//
//            return joPurReceive;
//        }
//        else {
            CheckListArray[] checkListArrays = checkListform.getCheckListArray();

            BigDecimal allMeter = new BigDecimal(0);

            BigDecimal maxJH = new BigDecimal(0);
            for (CheckListArray checkListArray: checkListArrays) {
                BigDecimal JH = new BigDecimal(checkListArray.getSeq());
                if(JH.compareTo(maxJH) > 0)
                {
                    maxJH = JH;
                }
                allMeter = allMeter.add(new BigDecimal(checkListArray.getMeter()));
            }

            JSONArray ja = selectForm.selectCheckJoinQty(checkListform.getReceiveFEntryId());
            JSONObject joPurReceive = new JSONObject();
            if(ja.size() > 0)
            {
                JSONArray ja0 = (JSONArray) ja.get(0);
                BigDecimal checkJoinQty = ja0.getBigDecimal(0);
                BigDecimal qty6 = ja0.getBigDecimal(1);
                if(maxJH.compareTo(qty6) > 0)
                {
                    JSONObject isSuccess = new JSONObject();
                    isSuccess.put("IsSuccess", false);
                    JSONArray errors = new JSONArray();
                    JSONObject message = new JSONObject();
                    message.put("Message", "卷号超出交货辅助数量！");
                    errors.add(message);
                    isSuccess.put("Errors", errors);
                    JSONObject responseStatus = new JSONObject();
                    responseStatus.put("ResponseStatus", isSuccess);
                    joPurReceive.put("Result", responseStatus);

                    return joPurReceive;
                }
                BigDecimal actReceiveQty = ja0.getBigDecimal(2);
                BigDecimal qty = ja0.getBigDecimal(3);
                BigDecimal qty1 = ja0.getBigDecimal(4);
                BigDecimal qty2 = ja0.getBigDecimal(5);
                Form purReceiveForm=  purReceiveUpdateQty(Integer.parseInt(checkListform.getReceiveFid()),
                        Integer.parseInt(checkListform.getReceiveFEntryId()),checkJoinQty.add(allMeter),qty,qty1,qty2);
                joPurReceive = kingdeeFormSaveSoso.purReceiveSSA(purReceiveForm);
            }
//            JSONObject isSuccess = new JSONObject();
//            isSuccess.put("IsSuccess", true);
//            JSONObject responseStatus = new JSONObject();
//            responseStatus.put("ResponseStatus", isSuccess);
//            joPurReceive.put("Result", responseStatus);
            return joPurReceive;
//        }
    }

    public Form purReceiveUpdateQty(int fid, int fEntryId, BigDecimal checkJoinQty, BigDecimal qty, BigDecimal qty1, BigDecimal qty2){
        Form form=new Form();
        DataEntity  dataEntity=new DataEntity();
        PurReceiveUpdateModel purReceiveUpdateModel=new PurReceiveUpdateModel();

        List<PurReceiveUpdateEntity> purReceiveUpdateEntities = new ArrayList<>();

        form.setFormid("PUR_ReceiveBill");
        form.setData(dataEntity);

        dataEntity.setNeedUpDateFields(new ArrayList());
        dataEntity.setNeedReturnFields( new ArrayList());
        dataEntity.setIsDeleteEntry("false");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(purReceiveUpdateModel);

        purReceiveUpdateModel.setFid(fid);
        purReceiveUpdateModel.setFDetailEntity(purReceiveUpdateEntities);

        PurReceiveUpdateEntity purReceiveUpdateEntity = new PurReceiveUpdateEntity();
        purReceiveUpdateEntity.setFEntryID(fEntryId);
        purReceiveUpdateEntity.setFActReceiveQty(checkJoinQty);
        purReceiveUpdateEntity.setF_SUWI_Qty(qty);
        purReceiveUpdateEntity.setF_SUWI_Qty1(qty1);
        purReceiveUpdateEntity.setF_SUWI_Qty2(qty2);

        purReceiveUpdateEntities.add(purReceiveUpdateEntity);

        return form;
    }

}
