package com.chenmasoft.kingdeetofeishu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenmasoft.kingdeetofeishu.apiRequst.KingdeeApi;
import com.chenmasoft.kingdeetofeishu.dao.entity.CheckList;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service
public class KingdeeFormSaveSoso {
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private FishuformService fishuformService;
    @Autowired
    private LogUtil logUtil;
    public void  SSA(Form form,Fishuform fishuformFirst){
        String insCode=fishuformFirst.getInstanceCode();
        StringBuffer upStatus=new StringBuffer();
        JSONObject ResultSaveJson= kingdeeApi.kingdeeSave(form);
        JSONObject ResponseStatus=ResultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        //SuccessMessages
        if (ResponseStatus.getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = ResponseStatus.getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = ResponseStatus.getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = ResponseStatus.getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info("应付单" + PayBillSaveJsonid + "@@" + PayBillSaveJsonname + "保存成功");
            upStatus.append("飞书单号："+fishuformFirst.getSerialNumber()+"==>应付单:"+PayBillSaveJsonnumber+"保存成功;");
            //改写对照表中isuse 字段为已使用状态
            UpdateWrapper<Fishuform> fishuformUpdateWrapper =new UpdateWrapper<Fishuform>()
                    .eq("instance_code",fishuformFirst.getInstanceCode());
            Fishuform fishuform=new Fishuform();

            fishuform.setIsupload(upStatus.toString());
            fishuform.setUploadSuccess("1");
            fishuform.setIsokpayable(PayBillSaveJsonnumber);

            fishuformService.update(fishuform,fishuformUpdateWrapper);
            //保存成功后，进行提交审核，并下推付款单


         boolean aa=   submitForm(PayBillSaveJsonnumber,"AP_Payable",fishuformFirst);

         if(aa){
             boolean bb=    auditForm(PayBillSaveJsonnumber,"AP_Payable",fishuformFirst);
             if(bb){

                 boolean cc=    pushForm(PayBillSaveJsonnumber,"TSLY_payabletopaybill","AP_Payable","AP_PAYBILL",upStatus,fishuformFirst);
             if(cc){
                 JSONArray jsonArray=ResponseStatus.getJSONArray("SuccessMessages");
                 log.info("应付单" +"下推成功"+jsonArray.toString());

                // upStatus.append("下推成功"+PayBillSaveJson.toJSONString());
                 UpdateWrapper<Fishuform> fishuformUpdateWrapper2 =new UpdateWrapper<Fishuform>()
                         .eq("instance_code",insCode);
                 Fishuform fishuform2=new Fishuform();

//                 fishuform2.setIsupload(upStatus.toString());
                 fishuform2.setUploadSuccess("2");

                 fishuformService.update(fishuform2,fishuformUpdateWrapper);




                 //



             }else{
                 JSONArray jsonArray=ResponseStatus.getJSONArray("Errors");
                  log.error("应付单 "+PayBillSaveJsonnumber + "下推失败"+jsonArray.toJSONString());
                // logUtil.saveLog("应付单 :"+PayBillSaveJsonnumber + "下推失败;",fishuform.getInstanceCode(),fishuform.getApprovalCode(),fishuform.getApprovalName(),fishuform.toString());

                 UpdateWrapper<Fishuform> fishuformUpdateWrapper2 =new UpdateWrapper<Fishuform>()
                         .eq("instance_code",insCode);
                 Fishuform fishuform2=new Fishuform();
                  upStatus.append("=>下推失败");
                 fishuform2.setIsupload(upStatus.toString());
                  fishuform2.setUploadSuccess("-19");

                 fishuformService.update(fishuform2,fishuformUpdateWrapper);
             }

             }

             else{

                 log.error("应付单" +PayBillSaveJsonnumber +"审核失败");
                // logUtil.saveLog("应付单" +PayBillSaveJsonnumber +"审核失败",fishuform.getInstanceCode(),fishuform.getApprovalCode(),fishuform.getApprovalName(),fishuform.toString());

             }




         }else{


             log.error("应付单" + PayBillSaveJsonnumber+"提交失败");
             //logUtil.saveLog("应付单" + PayBillSaveJsonnumber+"提交失败",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"");

         }






        }else{
            JSONArray jsonArray=ResponseStatus.getJSONArray("Errors");
            log.error("应付单" + form.toString()+"保存失败;"+jsonArray.toJSONString());
            logUtil.saveLog("飞书:"+fishuformFirst.getSerialNumber()+"==>金蝶，保存失败",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因:"+jsonArray.toJSONString());


        }

    }

    public void  payAbleSSA(Form form,Fishuform fishuformFrist){
        String insCode=fishuformFrist.getInstanceCode();
         StringBuffer stringBuffer=new StringBuffer();
        JSONObject resultSaveJson= kingdeeApi.kingdeeSave(form);

        JSONObject jsonObject=resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info(insCode+"应付单 " + PayBillSaveJsonnumber + " 保存成功");
            //改写对照表中isuse 字段为已使用状态
            stringBuffer.append("飞书:"+fishuformFrist.getSerialNumber()+"==>应付单 " + PayBillSaveJsonnumber + " 保存成功");
            UpdateWrapper<Fishuform> fishuformUpdateWrapper =new UpdateWrapper<Fishuform>()
                    .eq("instance_code",insCode);
            Fishuform fishuform=new Fishuform();

            fishuform.setIsupload(stringBuffer.toString());
            fishuform.setUploadSuccess("1");
            fishuform.setIsokpayable(PayBillSaveJsonnumber);
            fishuformService.update(fishuform,fishuformUpdateWrapper);
            //保存成功后，进行提交 审核


            boolean aa=   submitForm(PayBillSaveJsonnumber,"AP_Payable",fishuformFrist);
            if(aa){
                boolean bb=    auditForm(PayBillSaveJsonnumber,"AP_Payable",fishuformFrist);
                   if(bb){

                   }else{
                       log.error(insCode+"应付单" + PayBillSaveJsonnumber+"审核失败");
                      // logUtil.saveLog("飞书:"+fishuformFrist.getSerialNumber()+"==>应付单" + PayBillSaveJsonnumber+"审核失败",fishuformFrist.getInstanceCode(),fishuformFrist.getSerialNumber(),fishuformFrist.getApprovalName(),"");

                   }


            }else{


                log.error(insCode+"应付单" + PayBillSaveJsonnumber+"提交失败");
               // logUtil.saveLog(insCode+"应付单" + PayBillSaveJsonnumber+"提交失败",fishuform.getInstanceCode(),fishuform.getApprovalCode(),fishuform.getApprovalName(),fishuform.toString());


            }






        }else{
            JSONArray jsonArray= jsonObject.getJSONArray("Errors");

            log.error(insCode+" 应付单 " +"保存失败"+jsonArray.toJSONString());

            logUtil.saveLog("飞书:"+fishuformFrist.getSerialNumber()+"==>金蝶应付单，保存失败",fishuformFrist.getInstanceCode(),fishuformFrist.getSerialNumber(),fishuformFrist.getApprovalName(),"失败原因:"+jsonArray.toJSONString());


        }


    }
    public void  otherPayAbleSSA(Form form,String insCode,Fishuform fishuformFrist){
        StringBuffer stringBuffer=new StringBuffer();
        JSONObject resultSaveJson= kingdeeApi.kingdeeSave(form);
        JSONObject jsonObject=resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = jsonObject.getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info("其他应付单" + PayBillSaveJsonid + "@@" + PayBillSaveJsonname + "保存成功");
            //改写对照表中isuse 字段为已使用状态
            stringBuffer.append(insCode+"其他应付单 " + PayBillSaveJsonnumber + "保存成功");
            UpdateWrapper<Fishuform> fishuformUpdateWrapper =new UpdateWrapper<Fishuform>()
                    .eq("instance_code",insCode);
            Fishuform fishuform=new Fishuform();

            fishuform.setIsupload(stringBuffer.toString());
            fishuform.setUploadSuccess("1");
            fishuform.setIsokotherpayable(PayBillSaveJsonnumber);
            fishuformService.update(fishuform,fishuformUpdateWrapper);
            //保存成功后，进行提交 审核


            boolean aa=   submitForm(PayBillSaveJsonnumber,"AP_OtherPayable",fishuformFrist);
            if(aa){
                boolean bb=    auditForm(PayBillSaveJsonnumber,"AP_OtherPayable",fishuformFrist);
               if(bb){
                   boolean cc=    pushForm(PayBillSaveJsonnumber,"TSLY_otherPayableToPaybill","AP_OtherPayable","AP_PAYBILL",stringBuffer,fishuformFrist);
                   if(cc){
                       log.info(insCode+"其他应付单" +PayBillSaveJsonnumber +"下推成功");

                     //  stringBuffer.append("飞书："+fishuformFrist.getSerialNumber()+"=>其他应付单:" +PayBillSaveJsonnumber +"下推成功");
                       UpdateWrapper<Fishuform> fishuformUpdateWrapper2 =new UpdateWrapper<Fishuform>()
                               .eq("instance_code",insCode);
                       Fishuform fishuform2=new Fishuform();

                     //  fishuform2.setIsupload(stringBuffer.toString());
                       fishuform2.setUploadSuccess("2");

                       fishuformService.update(fishuform2,fishuformUpdateWrapper);




                       //



                   }else{
                       log.error(insCode+"其他应付单" +PayBillSaveJsonnumber + "下推失败");
                       logUtil.saveLog(insCode+"其他应付单" +PayBillSaveJsonnumber + "下推失败",fishuform.getInstanceCode(),fishuform.getApprovalCode(),fishuform.getApprovalName(),fishuform.toString());

                       UpdateWrapper<Fishuform> fishuformUpdateWrapper2 =new UpdateWrapper<Fishuform>()
                               .eq("instance_code",insCode);
                       Fishuform fishuform2=new Fishuform();
                       stringBuffer.append("飞书："+fishuformFrist.getSerialNumber()+"=>其他应付单:" +PayBillSaveJsonnumber +"下推失败");
                       fishuform2.setIsupload(stringBuffer.toString());
                       fishuform2.setUploadSuccess("-19");

                       fishuformService.update(fishuform2,fishuformUpdateWrapper);
                   }

               }else {


               }


            }else{


                log.error("其他应付" +"提交失败");
                //logUtil.saveLog("其他应付" +"提交失败",null,null,null,null);

            }






        }else{
           JSONArray jsonArray= jsonObject.getJSONArray("Errors");
            log.error("其他应付" + form.toString()+"保存失败"+jsonArray.toJSONString());
            logUtil.saveLog("飞书:"+fishuformFrist.getSerialNumber()+"==>金蝶其他应付，保存失败",fishuformFrist.getInstanceCode(),fishuformFrist.getSerialNumber(),fishuformFrist.getApprovalName(),"失败原因:"+jsonArray.toJSONString());

            UpdateWrapper<Fishuform> fishuformUpdateWrapper =new UpdateWrapper<Fishuform>()
                    .eq("instance_code",insCode);
            Fishuform fishuform=new Fishuform();
               stringBuffer.append("飞书："+fishuformFrist.getSerialNumber()+"=>其他应付单:"  +"保存失败");
            fishuform.setIsupload(stringBuffer.toString());
            fishuform.setUploadSuccess("-1");

        }



    }

    public JSONObject checkListSSA(Form form){
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject resultSaveJson = kingdeeApi.kingdeeSave(form);
        JSONObject jsonObject = resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {
            log.info("检验单" + form.toString() + "保存成功");
        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("Errors");
            log.error("检验单" + form.toString() + "保存失败" + jsonArray.toJSONString());
        }
        return resultSaveJson;
    }



    public JSONObject purReceiveSSA(Form form){
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject resultSaveJson = kingdeeApi.kingdeeSave(form);
        JSONObject jsonObject = resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {
            log.info("收料通知单" + form.toString() + "保存成功");
        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("Errors");
            log.error("收料通知单" + form.toString() + "保存失败" + jsonArray.toJSONString());
        }
        return resultSaveJson;
    }

    public void  barCodeSSA(Form form){
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject resultSaveJson = kingdeeApi.kingdeeSave(form);
        JSONObject jsonObject = resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {
            log.info("条码" + form.toString() + "保存成功");
        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("Errors");
            log.error("条码" + form.toString() + "保存失败" + jsonArray.toJSONString());
        }
    }

    public boolean submitForm(String number,String formID,Fishuform fishuform){


        Submit submitform = new Submit();
        submitform.setCreateOrgId(0);
        submitform.setNumbers(new ArrayList<String>() {{
            add(number);
        }});//填写单据编号
        submitform.setIds("");
//        submitform.setSelectedPostId(0);
        submitform.setNetworkCtrl("");
        Form formSubmit = new Form();
        formSubmit.setFormid(formID);//"AP_Payable"
        formSubmit.setData(submitform);

        String submitRes= kingdeeApi.kingdeeSubmit(formSubmit);
        JSONObject jsonObject=JSON.parseObject(submitRes);
        JSONObject submitJsObj=jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus");
      boolean isok=  submitJsObj.getBoolean("IsSuccess");
     if(isok){
    String PayBillsubmitJsonid = submitJsObj.getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
    String PayBillsubmitJsonname = submitJsObj.getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
    String PayBillsubmitJsonnumber = submitJsObj.getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");
         log.info(formID +number + "提交成功"+jsonObject.toJSONString());
    return true;
}else{
         JSONArray jsonArray= submitJsObj.getJSONArray("Errors");




         log.error(formID +number + "提交失败"+jsonObject.toJSONString());
         String formType=new String();

         switch (formID){
             case("AP_Payable"):
                 formType="应付单";
                 break;
             case("AP_OtherPayable"):
                 formType="其他应付单";
                 break;
         }
         logUtil.saveLog("飞书:"+fishuform.getSerialNumber()+"==>金蝶"+formType+":" +number + "提交失败",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),"失败原因"+jsonArray.toJSONString());

         return false;
     }

    }


    public boolean auditForm(String number,String formID,Fishuform fishuform){

        Audit auditform = new Audit();
        auditform.setCreateOrgId(0);
        auditform.setNumbers(new ArrayList<String>() {{
            add(number);
        }});
        auditform.setIds("");
        auditform.setInterationFlags("");
        auditform.setNetworkCtrl("");
        auditform.setIsVerifyProcInst("");
        Form formAudit = new Form();
        formAudit.setFormid(formID);  //formID  "AP_Payable"
        formAudit.setData(auditform);

        String submitRes= kingdeeApi.kingdeeAudit(formAudit);
        JSONObject jsonObject=JSON.parseObject(submitRes);
        boolean isok=  jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess");
        if(isok){
            String PayBillSaveJsonid = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");
            log.info(formID +number+ "审核成功"+jsonObject.toJSONString());
            return true;
        }else{
            JSONArray jsonArray= jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors");

            log.error(formID +number + "审核失败"+jsonObject.toJSONString());

            //logUtil.saveLog(formID +"   "+number + "  审核失败"+jsonArray.toJSONString(),null,null,null,null);
            String formType=new String();
            switch (formID){
                case("AP_Payable"):
                    formType="应付单";
                    break;
                case("AP_OtherPayable"):
                    formType="其他应付单";
                    break;
            }
            logUtil.saveLog("飞书:"+fishuform.getSerialNumber()+"==>金蝶"+formType+":" +number + "审核失败",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),"失败原因"+jsonArray.toJSONString());

            return false;
        }


    }

    public boolean pushForm(String number,String RuleId,String formID,String TargetFormId,StringBuffer stringBuffer,Fishuform fishuform){

        //下推
        Push pushObj = new Push();
        pushObj.setIds("");
        pushObj.setNumbers(new ArrayList<String>() {{
            add(number);
        }});
        pushObj.setEntryIds("");
        pushObj.setRuleId(RuleId);//"TSLY_payabletopaybill","AP_Payable","AP_PAYBILL"
        pushObj.setTargetBillTypeId("");
        pushObj.setTargetOrgId(0);
        pushObj.setTargetFormId(TargetFormId);//  "AP_PAYBILL"
        pushObj.setIsEnableDefaultRule("false");
        pushObj.setIsDraftWhenSaveFail("true");
        pushObj.setCustomParams(new CustomParamsEntity());
        Form pushForm = new Form();
        pushForm.setFormid(formID);//formID  "AP_Payable"
        pushForm.setData(pushObj);


        String submitRes= kingdeeApi.kingdeePush(pushForm);
        JSONObject jsonObject=JSON.parseObject(submitRes);
        boolean isok=  jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess");
        if(isok){
            String PayBillSaveJsonid = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");
            log.info(formID +number+ "下推成功"+jsonObject.toJSONString());
            //修改备注
            UpdateWrapper<Fishuform> fishuformUpdateWrapper2 =new UpdateWrapper<Fishuform>()
                    .eq("instance_code",fishuform.getInstanceCode());
            Fishuform fishuform2=new Fishuform();
            stringBuffer.append("==>下推成功,生成付款单id:"+PayBillSaveJsonid);

            fishuform2.setIsupload(stringBuffer.toString());
             fishuform2.setIsokpaybill(PayBillSaveJsonid);

            fishuformService.update(fishuform2,fishuformUpdateWrapper2);




            return true;
        }else{
            JSONArray jsonArray= jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors");

            log.error(formID +number + "下推失败"+jsonObject.toJSONString());
            logUtil.saveLog("飞书:"+fishuform.getSerialNumber()+"==>表单："+number + "下推失败",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(), "失败原因"+jsonArray.toJSONString());

            //"飞书单号："+fishuformFirst.getSerialNumber()+"==>应付单:"+PayBillSaveJsonnumber+"保存成功;"
            return false;
        }


    }

    public String ssaSupplier(String orgid,String name,Fishuform fishuformFirst ){

        FBankInfoEntity addfBankInfoEntity = new FBankInfoEntity();
        addfBankInfoEntity.setFBankCountry(new HashMap<String, String>() {{
            put("Fnumber", "China");
        }});
//        addfBankInfoEntity.setFBankCode(widgetAccountNumber);
//        addfBankInfoEntity.setFBankHolder(widgetAccountName);
        //addfBankInfoEntity.setFOpenBankName(widgetAccountBankNam + widgetAccountBankBranch);
        addfBankInfoEntity.setFBankIsDefault(false);
        Supplier addSupplier = new Supplier();
        addSupplier.setFCreateOrgId(new HashMap<String, String>() {{
            put("Fnumber", "100");
        }});
        addSupplier.setFUseOrgId(new HashMap<String, String>() {{
            put("Fnumber", "100");
        }});
        addSupplier.setFGroup(new HashMap<String, String>() {{
            put("Fnumber", "GYS-001");
        }});
        addSupplier.setFName(name);
        addSupplier.setFBankInfo(new ArrayList<FBankInfoEntity>() {{
            add(addfBankInfoEntity);
        }});
        DataEntity dataEntity = new DataEntity();
        dataEntity.setNeedReturnFields(new ArrayList<>());
        dataEntity.setNeedUpDateFields(new ArrayList<>());
        dataEntity.setIsDeleteEntry("true");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(addSupplier);
        Form supplierForm = new Form();
        supplierForm.setFormid("BD_Supplier");
        supplierForm.setData(dataEntity);
//
        JSONObject saveReturnObj=    kingdeeApi.kingdeeSave(supplierForm);
      //  String saveReturn = kingdeeLoginByUser.kingdeeSave(supplierFormJson, cookieSupplier);
      //  JSONObject saveReturnObj = JSON.parseObject(saveReturn);
        if (saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            // String PayBillSaveJsonname = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info("供应商" + PayBillSaveJsonid + "@@" + PayBillSaveJsonnumber + "保存成功");
            //改写对照表中isuse 字段为已使用状态

            //保存成功后
            Submit submitform = new Submit();
            submitform.setCreateOrgId(0);
            submitform.setNumbers(new ArrayList<String>() {{
                add(PayBillSaveJsonnumber);
            }});//填写单据编号
            submitform.setIds("");
//            submitform.setSelectedPostId(0);
            submitform.setNetworkCtrl("");
            Form formSubmit = new Form();
            formSubmit.setFormid("BD_Supplier");
            formSubmit.setData(submitform);
//            String formSubmitStr=JSONObject.toJSONString(formSubmit);
//            String submitStr = kingdeeLoginByUser.kingdeeSubmit(formSubmitStr, cookieSupplier);
//            JSONObject submitJobj = JSON.parseObject(submitStr);
            String submitStr= kingdeeApi.kingdeeSubmit(formSubmit);
            JSONObject submitJobj = JSON.parseObject(submitStr);
            JSONObject submitForm=submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus");
            if (submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

                String PayBillsubmitJsonid = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
                // String PayBillsubmitJsonname = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
                String PayBillsubmitJsonnumber = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

                log.info("供应商" + PayBillsubmitJsonid + "@@" + PayBillsubmitJsonnumber + "提交成功");
                Audit auditform = new Audit();
                auditform.setCreateOrgId(0);
                auditform.setNumbers(new ArrayList<String>() {{
                    add(PayBillsubmitJsonnumber);
                }});
                auditform.setIds("");
                auditform.setInterationFlags("");
                auditform.setNetworkCtrl("");
                auditform.setIsVerifyProcInst("");
                Form formAudit = new Form();
                formAudit.setFormid("BD_Supplier");
                formAudit.setData(auditform);
                String auditFormStr= kingdeeApi.kingdeeAudit(formAudit);
//                String auditFormStr =  formAuditjson=JSONObject.toJSONString(formAudit);
//                String auditFormStr = kingdeeLoginByUser.kingdeeAudit(formAuditjson, cookieSupplier);
                JSONObject auditFormJobj = JSON.parseObject(auditFormStr);
                JSONObject auditForm=auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus");
                if (auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

                    String PayBillauditJsonid = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
                    String PayBillauditJsonname = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
                     String PayBillauditJsonnumber = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

                    log.info("供应商" + PayBillauditJsonid + "@@" + PayBillauditJsonnumber + PayBillauditJsonname + "审核成功");
                    //此处增加自动分配供应商

                    autoOperation("BD_Supplier",PayBillauditJsonid,fishuformFirst);


                      return PayBillauditJsonnumber;


                    //                           CustomerSupplier customerSupplier=new CustomerSupplier();
//re
//                           customerSupplier.setFnumber(PayBillauditJsonnumber);
//                           customerSupplier.setName(widgetAccountName);
//                           customerSupplier.setSimname("");
//                           customerSupplier.setGrouping("");
//                           customerSupplier.setUse_org(fishu1);
//
//                            if(customerSupplierMapper.insertdata(customerSupplier))
//                            {
//                                customerSuppliers = customerSupplierMapper.selectCustomerSupplier(widgetAccountName);
//                            }


                } else {
                    JSONArray auditError=auditForm.getJSONArray("Errors");
                    log.error("新增供应商"+name+" 审核失败,"+auditFormStr);
                    logUtil.saveLog("新增供应商"+name+" 审核失败" ,fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因"+auditError.toString());

                }
            } else {
                JSONArray submitError=submitForm.getJSONArray("Errors");

                log.error("新增供应商"+name+"  提交失败,"+submitStr);
                logUtil.saveLog("新增供应商 "+name+" 提交失败",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因"+submitError.toString());


            }}else {
           String err= saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors").toString();
            log.error("新增供应商"+name+"保存失败,"+saveReturnObj.toJSONString());
            logUtil.saveLog("新增供应商 "+name+" 保存失败",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因"+err);

        }

        return null;
    }
    //新增客户方法
    public String ssaCustomber(String orgid,String name ,Fishuform fishuformFirst ){
        Form supplierForm = new Form();
        DataEntity dataEntity = new DataEntity();
        CustomSSA customSSA=new CustomSSA();



        supplierForm.setFormid("BD_Customer");
        supplierForm.setData(dataEntity);

        dataEntity.setNeedReturnFields(new ArrayList<>());
        dataEntity.setNeedUpDateFields(new ArrayList<>());
        dataEntity.setIsDeleteEntry("true");
        dataEntity.setSubSystemId("");
        dataEntity.setIsVerifyBaseDataField("false");
        dataEntity.setIsEntryBatchFill("true");
        dataEntity.setValidateFlag("true");
        dataEntity.setNumberSearch("true");
        dataEntity.setIsAutoAdjustField("false");
        dataEntity.setInterationFlags("");
        dataEntity.setIgnoreInterationFlag("");
        dataEntity.setModel(customSSA);

       // customSSA.setFCUSTID(0);
        customSSA.setFCreateOrgId(new HashMap<String, String>() {{
            put("Fnumber", "100");
        }});
       // customSSA.setFNumber(name);
        customSSA.setFUseOrgId(new HashMap<String, String>() {{
            put("Fnumber", "100");
        }});
        customSSA.setFName(name);
        customSSA.setFGroup(new HashMap<String, String>() {{
            put("Fnumber", "300");
        }});

        JSONObject saveReturnObj=    kingdeeApi.kingdeeSave(supplierForm);
        //  String saveReturn = kingdeeLoginByUser.kingdeeSave(supplierFormJson, cookieSupplier);
        //  JSONObject saveReturnObj = JSON.parseObject(saveReturn);
        if (saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            // String PayBillSaveJsonname = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info("新增客户" + PayBillSaveJsonid + "@@" + PayBillSaveJsonnumber + "保存成功");
            //改写对照表中isuse 字段为已使用状态

            //保存成功后
            Submit submitform = new Submit();
            submitform.setCreateOrgId(0);
            submitform.setNumbers(new ArrayList<String>() {{
                add(PayBillSaveJsonnumber);
            }});//填写单据编号
            submitform.setIds("");
//            submitform.setSelectedPostId(0);
            submitform.setNetworkCtrl("");
            Form formSubmit = new Form();
            formSubmit.setFormid("BD_Customer");
            formSubmit.setData(submitform);
//            String formSubmitStr=JSONObject.toJSONString(formSubmit);
//            String submitStr = kingdeeLoginByUser.kingdeeSubmit(formSubmitStr, cookieSupplier);
//            JSONObject submitJobj = JSON.parseObject(submitStr);
            String submitStr= kingdeeApi.kingdeeSubmit(formSubmit);
            JSONObject submitJobj = JSON.parseObject(submitStr);
           JSONObject submitForm= submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus");
            if (submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

                String PayBillsubmitJsonid = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
                // String PayBillsubmitJsonname = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
                String PayBillsubmitJsonnumber = submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

                log.info("新增客户" + PayBillsubmitJsonid + "@@" + PayBillsubmitJsonnumber + "提交成功");
                Audit auditform = new Audit();
                auditform.setCreateOrgId(0);
                auditform.setNumbers(new ArrayList<String>() {{
                    add(PayBillsubmitJsonnumber);
                }});
                auditform.setIds("");
                auditform.setInterationFlags("");
                auditform.setNetworkCtrl("");
                auditform.setIsVerifyProcInst("");
                Form formAudit = new Form();
                formAudit.setFormid("BD_Customer");
                formAudit.setData(auditform);
                String auditFormStr= kingdeeApi.kingdeeAudit(formAudit);
//                String auditFormStr =  formAuditjson=JSONObject.toJSONString(formAudit);
//                String auditFormStr = kingdeeLoginByUser.kingdeeAudit(formAuditjson, cookieSupplier);
                   JSONObject auditFormJobj = JSON.parseObject(auditFormStr);
                   JSONObject auditForm=auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus");
                if (auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

                    String PayBillauditJsonid = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
                    String PayBillauditJsonname = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
                    String PayBillauditJsonnumber = auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

                    log.info("新增客户" + PayBillauditJsonid + "@@" + PayBillauditJsonnumber + PayBillauditJsonname + "审核成功");

                    ///此处增加一个自动客户分配的功能
                    autoOperation("BD_Customer",PayBillauditJsonid,fishuformFirst);


                    return PayBillauditJsonnumber;


                    //                           CustomerSupplier customerSupplier=new CustomerSupplier();
//re
//                           customerSupplier.setFnumber(PayBillauditJsonnumber);
//                           customerSupplier.setName(widgetAccountName);
//                           customerSupplier.setSimname("");
//                           customerSupplier.setGrouping("");
//                           customerSupplier.setUse_org(fishu1);
//
//                            if(customerSupplierMapper.insertdata(customerSupplier))
//                            {
//                                customerSuppliers = customerSupplierMapper.selectCustomerSupplier(widgetAccountName);
//                            }


                } else {
                    JSONArray auditError=auditForm.getJSONArray("Errors");
                    log.error("新增客户审核失败,"+auditError.toString());
                    logUtil.saveLog("新增客户审核失败,",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因："+auditError.toString());

                }
            } else {
                JSONArray  submitError= submitForm.getJSONArray("Errors");

                log.error("新增客户提交失败,"+submitError.toString());
                logUtil.saveLog("新增客户提交失败"  ,fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因："+submitError.toString());

            }}else {
          String err=  saveReturnObj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors").toString();
            log.error("新增客户保存失败,"+saveReturnObj.toJSONString());
            logUtil.saveLog("新增客户保存失败",fishuformFirst.getInstanceCode(),fishuformFirst.getSerialNumber(),fishuformFirst.getApprovalName(),"失败原因:"+err);

        }










        return null;
    }


    public void autoOperation(String formid,String fid,Fishuform fishuformFrist){
        Form form =new Form();
        HashMap<String,Object> hashMap=new HashMap();
        form.setFormid(formid);
        form.setData(hashMap);
        //1,100010,100753,242129,280666,280670
//        {
//            "PkIds": 0,
//                "TOrgIds": ""
//        }
        hashMap.put("PkIds",fid);
        hashMap.put("TOrgIds","100010,100753,242129,280666,280670");
              String eAllocate=kingdeeApi.kingdeeAllocate(form);
          JSONObject jsonObject=JSON.parseObject(eAllocate);
       boolean isok= jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess");
      if(isok){
          log.info(formid+fid+"分配成功");

        JSONArray jsonArray= jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys");


//              "Id": "304443",
//                      "Number": "VEN00093",

           String   fidenum= jsonArray.getJSONObject(0).getString("Number");
          SUppilerandssa(fidenum,formid,fishuformFrist);




      }else{
          JSONArray jsonArray= jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("Errors");

          log.error(formid+" "+fid+"分配失败");
          logUtil.saveLog("飞书："+fishuformFrist.getSerialNumber()+"供应商或者客户分配失败"+jsonArray.toString(),fishuformFrist.getInstanceCode(),fishuformFrist.getSerialNumber(),fishuformFrist.getApprovalName(),"失败原因："+jsonArray.toJSONString());

      }

    }
    //"BD_Customer"
    public void SUppilerandssa(String number,String formid,Fishuform fishuform){
        //保存成功后
        Submit submitform = new Submit();
        submitform.setCreateOrgId(0);
        submitform.setNumbers(new ArrayList<String>() {{
            add(number);
        }});//填写单据编号

        submitform.setIds("");
//        submitform.setSelectedPostId(0);
        submitform.setNetworkCtrl("");
        Form formSubmit = new Form();
        formSubmit.setFormid(formid);
        formSubmit.setData(submitform);
//            String formSubmitStr=JSONObject.toJSONString(formSubmit);
//            String submitStr = kingdeeLoginByUser.kingdeeSubmit(formSubmitStr, cookieSupplier);
//            JSONObject submitJobj = JSON.parseObject(submitStr);
        String submitStr= kingdeeApi.kingdeeSubmit(formSubmit);
        JSONObject submitJobj = JSON.parseObject(submitStr);
       JSONObject submitForm= submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").size()>0) {
         String success=   submitJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").toString();

            log.info("新增客户供应商分配后" + success + "提交成功");
            Audit auditform = new Audit();
            auditform.setCreateOrgId(0);
            auditform.setNumbers(new ArrayList<String>() {{
                add(number);
            }});
            auditform.setIds("");
            auditform.setInterationFlags("");
            auditform.setNetworkCtrl("");
            auditform.setIsVerifyProcInst("");
            Form formAudit = new Form();
            formAudit.setFormid(formid);
            formAudit.setData(auditform);
            String auditFormStr= kingdeeApi.kingdeeAudit(formAudit);
//                String auditFormStr =  formAuditjson=JSONObject.toJSONString(formAudit);
//                String auditFormStr = kingdeeLoginByUser.kingdeeAudit(formAuditjson, cookieSupplier);
            JSONObject auditFormJobj = JSON.parseObject(auditFormStr);
            JSONObject auditForm=auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus");

            if (auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").size()>0) {
              String aa=  auditFormJobj.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").toString();

                log.info("新增客户供应商分配后" + aa + "审核成功");




                return ;


                //                           CustomerSupplier customerSupplier=new CustomerSupplier();
//re
//                           customerSupplier.setFnumber(PayBillauditJsonnumber);
//                           customerSupplier.setName(widgetAccountName);
//                           customerSupplier.setSimname("");
//                           customerSupplier.setGrouping("");
//                           customerSupplier.setUse_org(fishu1);
//
//                            if(customerSupplierMapper.insertdata(customerSupplier))
//                            {
//                                customerSuppliers = customerSupplierMapper.selectCustomerSupplier(widgetAccountName);
//                            }


            } else {
                JSONArray auditErrors=auditForm.getJSONArray("Errors");
                log.error("新增客户供应商分配后审核失败,"+auditErrors.toString());
                logUtil.saveLog("新增客户分配后审核失败,",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),"失败原因"+auditErrors.toString());

            }
        } else {
            JSONArray submitError=submitForm.getJSONArray("Errors");
            log.error("新增客户供应商分配后提交失败,"+submitStr);
            logUtil.saveLog("新增客户供应商分配后提交失败," ,fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),"失败原因"+submitError.toString());

        }



    }


    }

