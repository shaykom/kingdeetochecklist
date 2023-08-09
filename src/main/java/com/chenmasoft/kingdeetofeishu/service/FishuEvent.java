package com.chenmasoft.kingdeetofeishu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenmasoft.kingdeetofeishu.apiRequst.FeishuApi;
import com.chenmasoft.kingdeetofeishu.dao.entity.*;
import com.chenmasoft.kingdeetofeishu.dao.service.*;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.EventMsg;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.GetEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FishuEvent {
    @Autowired
    private EventService eventService;
    @Autowired
    private FeishuApi feishuApi;
    @Autowired
    private FishuformService fishuformService;
    @Autowired
    private FishuformentryService fishuformentryService;
    @Autowired
    private SelectForm selectForm;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private SaveTokingdee saveTokingdee;
    @Autowired
    private ExpenseDepartmentService expenseDepartmentService;
    @Autowired
    private LogUtil logUtil;
    @Autowired
    private FidepartmentService fidepartmentService;
    @Value("${feishu.approval_code_reimbursement}")
    private String approval_code_reimbursement;//报销编号
    @Value("${feishu.approval_code_payment}")
    private String approval_code_payment;//付款编号
    @Async
   public void getEvent(JSONObject jsonObject){

        //1.保存event到数据库

        if(jsonObject==null||jsonObject.isEmpty()){
            return ;
        }
        JSONObject getJsonEvent= new JSONObject();
        try {
            getJsonEvent = jsonObject.getJSONObject("event");
            if(getJsonEvent.isEmpty()){
                log.error("接收到的json信息不符合要求");
               // logUtil.saveLog("接收到的json信息不符合要求","","","",jsonObject.toJSONString());
                return;
            }
        }catch (Exception e){

            e.getMessage();
            log.error("接收到的json信息是错误的");
            //logUtil.saveLog("接收到的json信息是错误的","","","",jsonObject.toJSONString());

            return;

        }
        if(!"approval_instance".equals(getJsonEvent.getString("type"))){
            log.error("无关的事件类型"+getJsonEvent.getString("type"));
           // logUtil.saveLog("无关的事件类型"+getJsonEvent.getString("type"),"","","",jsonObject.toJSONString());

            return;

        }

        EventMsg getEvent=new EventMsg();
try {
     getEvent= getJsonEvent.toJavaObject(EventMsg.class);

}catch (Exception e){

    log.error("不符合要求的JSON");
   // logUtil.saveLog("不符合要求的JSON","","","",jsonObject.toJSONString());

    return;
}


        saveTodb(getEvent);

        // 2.从数据库查询事件详情的实例，得到详情保存到数据库
        QueryWrapper<Event> eventQueryWrapper=new QueryWrapper<Event>()
                .eq("status","APPROVED")
                .eq("instance_code",getEvent.getInstance_code())
                .isNull("isuse");

       //  List<Event> events= eventService.list(eventQueryWrapper);


           Event event= eventService.getOne(eventQueryWrapper);
           if(event==null){
               return;
           }
            saveFishuSome(event);// 存储事件到数据库
            saveTokingdee.selectOneByFeishu(event.getInstanceCode());



//        for (Event item:
//                events) {
//
//            try {
////               查询飞书事件存储到数据库
//                saveFishuSome(item);
//
//            saveTokingdee.selectOneByFeishu();
//
//            }catch (Exception e){
//
//                log.info("查询飞书事件存储到数据库出现异常，："+e.getMessage());
//                continue;
//            }
//
//
//
//        }
////        3.从数据库查询出事件详情保存到金蝶
//        log.info("...............");
//            saveTokingdee.selectForm();



}

    @Async
    public void getEvent2QueryList(){


        // 1.从数据库查询事件详情的实例，得到详情保存到数据库
        QueryWrapper<Event> eventQueryWrapper=new QueryWrapper<Event>()
                .eq("status","APPROVED")
                .isNull("isuse");

        List<Event> events= eventService.list(eventQueryWrapper);




        for (Event item:
                events) {

            try {
//               查询飞书事件存储到数据库
                saveFishuSome(item);


            }catch (Exception e){

                log.info("查询飞书事件存储到数据库出现异常，："+e.getMessage());
                continue;
            }

        }
////        3.从数据库查询出事件详情保存到金蝶
//        log.info("...............");
//            saveTokingdee.selectForm();


    }



      //存储单个事件详情到数据库
    public void saveFishuSome(Event item) {




        String  resultFishu=  feishuApi.singleEvnt(item.getInstanceCode())  ;
if(resultFishu==null){
    return;
}
          JSONObject jsonObject=JSON.parseObject(resultFishu);

     if(jsonObject.getInteger("code")==0) {
        JSONObject  fishuformjsonObject= jsonObject.getJSONObject("data");
        String formString= fishuformjsonObject.getString("form");
         JSONArray formArray= JSON.parseArray(formString);
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

         Fishuform fishuform = new Fishuform();
         String foId=String.valueOf(new Date().getTime());
         fishuform.setId(foId);
         fishuform.setInstanceCode(item.getInstanceCode());
         fishuform.setApprovalCode(item.getApprovalCode());
         fishuform.setApprovalName(fishuformjsonObject.getString("approval_name"));

         fishuform.setDepartmentId(simpleDateFormat.format(Long.valueOf(fishuformjsonObject.getString("end_time"))));

         fishuform.setStatus(fishuformjsonObject.getString("status"));
         fishuform.setSerialNumber(fishuformjsonObject.getString("serial_number"));
         fishuform.setStartTim(fishuformjsonObject.getString("start_time"));
         fishuform.setEndTime(fishuformjsonObject.getString("end_time"));
         fishuform.setUuid(fishuformjsonObject.getString("uuid"));
         fishuform.setUserId(fishuformjsonObject.getString("user_id"));

         for (int i = 0; i <formArray.size() ; i++) {

           String   name=  formArray.getJSONObject(i).getString("name");
           switch (name){
               case "部门":
               case "费用所属部门":

                   String   value=  formArray.getJSONObject(i).getString("value");
                 JSONArray department=  JSON.parseArray(value);
                 String departName=  department.getJSONObject(0).getString("name");
                 String  open_id  = department.getJSONObject(0).getString("open_id");
                 QueryWrapper<Fidepartment> fidepartmentQueryWrapper=new QueryWrapper<Fidepartment>()
                         .eq("fishuDepartmentname",departName);

//                   Fidepartment fidepartment= fidepartmentService.getOne(fidepartmentQueryWrapper);
//                   fishuform.setKingdeeDepartment(fidepartment.getKingdeedepartmentnumber());
                   fishuform.setDepartmentName(departName);
                   fishuform.setDepartmentOpen_id(open_id);
                   fishuform.setDepartment(value);

                 String  fishuDepartment=selectForm.selectDepartment(open_id);
                   if(fishuDepartment==null||fishuDepartment.length()==0){
                       log.error(item.getInstanceCode()+"飞书部门在金蝶找不到与其对应的部门");
                       logUtil.saveLog(item.getInstanceCode()+"飞书部门在金蝶找不到与其对应的部门",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),fishuform.toString()+item.toString());
                   }else {
                       fishuform.setKingdeeDepartment(fishuDepartment);
                   }
                   break;
               case "付款账户名称":
                   String   value2=  formArray.getJSONObject(i).getString("value");
                   fishuform.setPayaccount(value2);
                     String orgnization =selectForm.selectOrgnization(value2);
                   if(orgnization==null||orgnization.length()==0){
                       log.error(item.getInstanceCode()+"飞书付款账户在金蝶找不到与其对应的组织");//客户或供应商
                       logUtil.saveLog(item.getInstanceCode()+"飞书付款账户在金蝶找不到与其对应的组织",fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),fishuform.toString()+item.toString());

                   }else {
                       fishuform.setPayAccountFnumber(orgnization);
                   }

                   break;
               case "收款账户账号":
                   String   widgetAccountName=  formArray.getJSONObject(i).getJSONObject("value").getString("widgetAccountName");
                   String   widgetAccountNumber=  formArray.getJSONObject(i).getJSONObject("value").getString("widgetAccountNumber");

                   fishuform.setRecaccount(widgetAccountNumber);
                   fishuform.setAccountname( widgetAccountName);
                   if(approval_code_reimbursement.equals(item.getApprovalCode())){
                     String tEmpinfo=  selectForm.selectEmpinfo(widgetAccountName,fishuform.getPayAccountFnumber(),fishuform);
                       if(tEmpinfo==null||tEmpinfo.length()==0){
                           log.error("找不到的客户，请重新填写"+widgetAccountName);
                           logUtil.saveLog("找不到的客户，请重新填写"+widgetAccountName,fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),fishuform.toString()+item.toString());

                       }else{
                          // log.error("飞书对应客户，"+tEmpinfo);
                           fishuform.setAccountNameNumber(tEmpinfo);
                       }

                   }else  if(approval_code_payment.equals(item.getApprovalCode())){

                      String tBD_Supplier=  selectForm.selectBD_Supplier(widgetAccountName,fishuform.getPayAccountFnumber(),fishuform);
                       if(tBD_Supplier==null||tBD_Supplier.length()==0){
                           log.error("找不到的供应商，请重新填写"+widgetAccountName);
                           logUtil.saveLog("找不到的供应商，请重新填写"+widgetAccountName,fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),fishuform.toString()+item.toString());

                       }else{
                         //  log.info("飞书对应供应商，"+tBD_Supplier);
                           fishuform.setAccountNameNumber(tBD_Supplier);
                       }


                   }
                   break;
               case "付款类型":
                   String   value4=  formArray.getJSONObject(i).getString("value");

                   fishuform.setPaytype(value4);
                   break;
               case "币种":
                   String   value6=  formArray.getJSONObject(i).getString("value");
                   fishuform.setCurrency(value6);
                     String fnumber1=  selectForm.selectCurrency(value6);
                     if(fnumber1==null||fnumber1.length()==0){
                         logUtil.saveLog("找不到的币种，请检查:"+value6,fishuform.getInstanceCode(),fishuform.getSerialNumber(),fishuform.getApprovalName(),fishuform.toString());

                     }else {
                         fishuform.setCurrencyFnumber(fnumber1);
                     }

               case "内容":
                   String  value5=  formArray.getJSONObject(i).getString("value");

                   fishuform.setFremark(value5);
                   break;
               case "费用明细":

                   JSONArray   entryList=  formArray.getJSONObject(i).getJSONArray("value");
                   saveFishuEntry(item.getInstanceCode(),foId,entryList,fishuform.getSerialNumber());
                   break;

           }


         }


         UpdateWrapper<Fishuform> fishuformUpdateWrapper=new UpdateWrapper<Fishuform>()
                 .eq("instance_code",item.getInstanceCode());
      boolean isok=   fishuformService.saveOrUpdate(fishuform,fishuformUpdateWrapper);
         if(isok){
             log.info("保存成功"+item.getInstanceCode());
             //反写event
             UpdateWrapper<Event> eventUpdateWrapper=new UpdateWrapper<Event>()
                     .eq("instance_code",item.getInstanceCode());
             Event upDateEvent=new Event();
             upDateEvent.setIsuse(1);
           boolean okk=  eventService.update(upDateEvent,eventUpdateWrapper);
           if(okk) {
               log.info("保存后反写成功" + item.getInstanceCode());
           }else
           { log.info("保存后反写失败" + item.getInstanceCode());
           }
         }else{
             log.info("保存失败"+item.getInstanceCode());
         }


     }
     else{
         return ;
     }



    }

    private void saveFishuEntry(String inscode,String souceid,JSONArray entryList,String fishuNumber) {
        for (int i = 0; i <entryList.size() ; i++) {
            Fishuformentry fishuformentry = new Fishuformentry();

            fishuformentry.setInstanceCode(inscode);
            fishuformentry.setSouceid(souceid);
            for (int j = 0; j < entryList.getJSONArray(i).size() ; j++) {
                try {





                    String name = entryList.getJSONArray(i).getJSONObject(j).getString("name");

                    switch (name) {

//                        case "费用部门":
//                            String value = entryList.getJSONArray(i).getJSONObject(j).getString("value");
//                            fishuformentry.setPaydepartment(value);
//                            QueryWrapper<ExpenseDepartment> expenseDepartmentQueryWrapper=new QueryWrapper<ExpenseDepartment>()
//                                    .eq("f_department",value);
//                            ExpenseDepartment expenseDepartment= expenseDepartmentService.getOne(expenseDepartmentQueryWrapper);
//                            fishuformentry.setPayDepartmentNumber(expenseDepartment.getKDepartment());
//                            break;
                        case "费用类型":
                            String value2 = entryList.getJSONArray(i).getJSONObject(j).getString("value");
                            fishuformentry.setMonytype(value2);
                            String tExpense=selectForm.selectExpense(value2);
                            if(tExpense==null||tExpense.length()==0){
                                log.error(fishuformentry.getInstanceCode()+"&&"+souceid+"无法获取费用类型 "+value2);
                                logUtil.saveLog("无法获取费用类型 :"+value2,fishuformentry.getInstanceCode(),fishuNumber,"",fishuformentry.toString());

                            }else{
                                fishuformentry.setMonyTypeNumber(tExpense);
                            }

                            break;
                        case "发票类型":
                            String value3 = entryList.getJSONArray(i).getJSONObject(j).getString("value");
                            fishuformentry.setFapiao(value3);


                            QueryWrapper<Invoice> invoiceQueryWrapper=new QueryWrapper<Invoice>()
                                    .eq("f_name",value3);
//                            Invoice invoice= invoiceService.getOne(invoiceQueryWrapper);

                             String fapiaonumber=  selectForm.selectFapiao(value3);
                            if(fapiaonumber==null||fapiaonumber.length()==0){
                                log.error(fishuformentry.getInstanceCode()+"&&"+souceid+"无法获取发票类型 "+value3);
                                logUtil.saveLog("无法获取发票类型: "+value3,fishuformentry.getInstanceCode(),fishuNumber,"",fishuformentry.toString());

                            }else{
                                fishuformentry.setFapiaoNumber(fapiaonumber);
                            }

                            break;
//                        case "币种":
//                            String value4 = entryList.getJSONArray(i).getJSONObject(j).getString("value");
//                            fishuformentry.setCurrency(value4);
//                             String currencyNumber =  selectForm.selectCurrency(value4);
//                            //根据飞书的币别查找金蝶的编号
//                            fishuformentry.setCurrencyFnumber(currencyNumber);
//                            break;
//                        case "内容":
//                            String value4 = entryList.getJSONArray(i).getJSONObject(j).getString("value");
//                            fishuformentry.setSomething(value4);
//                            break;
                        case "付款金额":
                            String value5 = entryList.getJSONArray(i).getJSONObject(j).get("value").toString();
                            fishuformentry.setMoney(value5);
                            Double money= Double.valueOf(value5);
                           if(money<=0){
                              // log.error(fishuformentry.getInstanceCode()+"&&"+souceid+"无法获取发票类型 "+value3);
                               logUtil.saveLog("付款的金额不能小于等于0 :"+money,fishuformentry.getInstanceCode(),fishuNumber,"",fishuformentry.toString());


                           }


                            break;

                    }

//                    UpdateWrapper<Fishuformentry> fishuformEntryUpdateWrapper = new UpdateWrapper<Fishuformentry>()
//                            .eq("instance_code", inscode);
//                    boolean isok = fishuformentryService.saveOrUpdate(fishuformentry, fishuformEntryUpdateWrapper);
//                    if (isok) {
//
//                        log.info("保存明细值成功：：" + inscode+name );
//
//                    } else {
//                        log.info("保存明细值失败：：" + inscode+name );
//
//                    }

                }catch (Exception e){



                    e.getMessage();
                    log.error("保存飞书单据明细值到数据库出现异常");
                    logUtil.saveLog("保存飞书单据明细值到数据库出现异常",fishuformentry.getInstanceCode(),fishuNumber,"",e.getStackTrace().toString()+e.getMessage());

                    continue;
                }


            }

                    boolean isok = fishuformentryService.save(fishuformentry);
                    if (isok) {

                        log.info("保存明细成功：：" + inscode+fishuformentry.toString() );

                    } else {

                        log.error("保存明细失败：：" + inscode+fishuformentry.toString() );
                        logUtil.saveLog("保存明细失败：：" + inscode ,"inscode","","",fishuformentry.toString());

                    }


        }




    }


    //存储事件到数据库
    private void saveTodb(EventMsg eventMsg) {
        Event event=new Event();
        event.setApprovalCode(eventMsg.getApproval_code());
        event.setTenantKey(eventMsg.getTenant_key());
        event.setInstanceCode(eventMsg.getInstance_code());
        event.setType(eventMsg.getType());
        event.setAppId(eventMsg.getApp_id());
        event.setUuid(eventMsg.getUuid());
        event.setOperateTime(eventMsg.getOperate_time());
        event.setStatus(eventMsg.getStatus());



       boolean isok =eventService.saveOrUpdate(event);
       if(isok){
            log.error("保存或更新event 成功"+event.getInstanceCode());
        }else{
            log.error("保存或更新event 失败"+event.getInstanceCode());
           //logUtil.saveLog("保存或更新event 事件失败"+event.getInstanceCode(),event.getInstanceCode(),event.getApprovalCode(),"",event.toString());

       }

    }

}
