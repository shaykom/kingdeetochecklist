package com.chenmasoft.kingdeetofeishu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenmasoft.kingdeetofeishu.apiRequst.KingdeeApi;
import com.chenmasoft.kingdeetofeishu.dao.entity.BarCodeForm;
import com.chenmasoft.kingdeetofeishu.dao.entity.CheckList;
import com.chenmasoft.kingdeetofeishu.dao.entity.Event;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.dao.service.EventService;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformentryService;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.Form;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SaveTokingdee {
    @Value("${feishu.approval_code_reimbursement}")
    private String approval_code_reimbursement;//报销编号
    @Value("${feishu.approval_code_payment}")
    private String approval_code_payment;//付款编号
    @Autowired
    private FishuformService fishuformService;
    @Autowired
    private FishuformentryService fishuformentryService;
    @Autowired
    private CreatForm creatForm;
    @Autowired
    private KingdeeFormSaveSoso kingdeeFormSaveSoso;
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private LogUtil logUtil;
public void  selectForm(){
    //
    log.info("从数据库中取数据保存单据到金蝶");
    QueryWrapper<Fishuform> fishuformQueryWrapper=new QueryWrapper<Fishuform>()
            .isNull("uploadSuccess");

      List<Fishuform> fishuformList=fishuformService.list(fishuformQueryWrapper);

    for (int i = 0; i <fishuformList.size() ; i++) {
        Fishuform fishuform =   fishuformList.get(i);
//try {
      saveKingdeeForm(fishuform );
//}catch (Exception e){
//
//    e.getMessage();
//    e.toString();
//    log.info("保存单据到金蝶出现异常");
//    continue;
//}



    }




}


    public void  selectFormByInsCode(String instance){




        //
        log.info("从数据库中取数据保存单据到金蝶"+instance);
        QueryWrapper<Fishuform> fishuformQueryWrapper=new QueryWrapper<Fishuform>()
                .isNull("uploadSuccess")
                .eq("instance_code",instance)
                .isNotNull("payAccountFnumber");//非空


      Fishuform fishuform=fishuformService.getOne(fishuformQueryWrapper);

            if(fishuform==null){
                log.error("不满足重传条件，已返回");
                logUtil.saveLog("不满足重传条件，已返回",instance,"","",instance);
                return;
            }

            saveKingdeeForm(fishuform);



        }
        public void selectOneByFeishu(String instance){

            log.info("从数据库中取数据保存单据到金蝶"+instance);
            //上传金蝶条件
            QueryWrapper<Fishuform> fishuformQueryWrapper=new QueryWrapper<Fishuform>()
                    .isNull("uploadSuccess")  //是空
                    .eq("instance_code",instance) //等于
                    .isNotNull("payAccountFnumber");//非空


            Fishuform fishuform=fishuformService.getOne(fishuformQueryWrapper);

            if(fishuform==null){
                log.error("不满足传单条件，已返回");
                logUtil.saveLog("不满足传单条件，已返回,付款账户为空或已上传过",instance,"","",instance);
                return;
            }

            saveKingdeeForm(fishuform);




        }






    public void saveKingdeeForm(Fishuform fishuform) {

       String provalCode= fishuform.getApprovalCode();
       if(provalCode.equals(approval_code_reimbursement)){
           //  approval_code_reimbursement;//报销编号
           saveReimbursement(fishuform);

       }else if(provalCode.equals(approval_code_payment)){
           //approval_code_payment;//付款编号 :
           savePayment(fishuform);


       }




    }

    private void savePayment(Fishuform fishuform) {
        //approval_code_payment;//付款编号 :
       String paytype=  fishuform.getPaytype();

       switch (paytype){


           case "有票付款":
               //应付单---》》付款单
           havePay(fishuform);

               break;
           case "预付付款":
               //付款单
             noPay(fishuform);
               break;
           case "核销无需付款":
               //应付单
               havePayEnd(fishuform);
               break;
       }


    }

    private void havePayEnd(Fishuform fishuform) {
        //生成应付单
        Form payAbleForm=  creatForm.payAble(fishuform);

        kingdeeFormSaveSoso.payAbleSSA(payAbleForm,fishuform);



    }

    private void noPay(Fishuform fishuform) {
        //生成付款单
        Form payBillForm = creatForm.payBill(fishuform);
        String PayJson = kingdeeApi.kingdeeDraft(payBillForm);
        JSONObject PayBillSaveJson = JSON.parseObject(PayJson);
        if (PayBillSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")) {

            String PayBillSaveJsonid = PayBillSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getLong("Id").toString();
            String PayBillSaveJsonname = PayBillSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("name");
            String PayBillSaveJsonnumber = PayBillSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus").getJSONArray("SuccessEntitys").getJSONObject(0).getString("Number");

            log.info("预付付款 暂存付款单" + PayBillSaveJsonid + "@@" + PayBillSaveJsonname + "成功");
            //改写对照表中isuse 字段为已使用状态
            UpdateWrapper<Fishuform> fishuformUpdateWrapper = new UpdateWrapper<Fishuform>()
                    .eq("instance_code", fishuform.getInstanceCode());
            Fishuform fishuformpp = new Fishuform();

            fishuformpp.setIsupload(fishuform.getSerialNumber()+"预付付款 暂存付款单" + PayBillSaveJsonid + "@@" + PayBillSaveJsonname + "成功");
            fishuformpp.setUploadSuccess("1");
            fishuformpp.setIsokpaybill(PayBillSaveJsonid);
            fishuformService.update(fishuformpp,fishuformUpdateWrapper);

        }
        else{


            log.error("预付付款 暂存付款单" + "失败");
            logUtil.saveLog("预付付款 暂存付款单" + "失败",fishuform.getInstanceCode(),fishuform.getApprovalCode(),fishuform.getApprovalName(),fishuform.toString());


        }
    }
    private void havePay(Fishuform fishuform) {



        //应付单下推付款单
        Form payAbleForm=  creatForm.payAble(fishuform);

        kingdeeFormSaveSoso.SSA(payAbleForm,fishuform);

    }

    private void saveReimbursement(Fishuform fishuform) {
        //  approval_code_reimbursement;//报销编号
        //其他应付单和付款单
        Form otherPayAbleForm=  creatForm.otherPayAble(fishuform);

        kingdeeFormSaveSoso.otherPayAbleSSA(otherPayAbleForm,fishuform.getInstanceCode(),fishuform);


//        Form payBillForm= creatForm.payBillTwo(fishuform);
//        kingdeeApi.kingdeeDraft(payBillForm);
    }

    public void saveCheckList(CheckList checkList) {
        //检验单
        Form checkListForm=  creatForm.checkList(checkList);
        kingdeeFormSaveSoso.checkListSSA(checkListForm);
    }

    public void saveBarCode(BarCodeForm barCode) {
        //检验单
        Form barCodeForm=  creatForm.barCode(barCode);
        kingdeeFormSaveSoso.barCodeSSA(barCodeForm);
    }


}
