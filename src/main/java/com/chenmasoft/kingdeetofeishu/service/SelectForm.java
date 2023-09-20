package com.chenmasoft.kingdeetofeishu.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenmasoft.kingdeetofeishu.apiRequst.KingdeeApi;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.ExecuteBillQuery;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.Form;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.Submit;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class SelectForm {
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private KingdeeFormSaveSoso kingdeeFormSaveSoso;

    public JSONArray selectBar_Code(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("PUR_ReceiveBill");
        executeBillQuery.setFieldKeys("FBillNo,FSupplierId.FName,FMaterialId.FNumber,FMaterialId.FName,F_WSL_EWM,FMateriaModel," +
                "FStockOrgId.FNumber,FUnitID.FNumber,FBillTypeID.FNumber,FSupplierId.FNumber,FAuxPropId,FLot.FNumber," +
                "FExtAuxUnitId.FNumber,F_SUWI_Qty6,FBaseUnitId.FNumber,FActReceiveQty,FID,FDetailEntity_FEntryID");
        executeBillQuery.setFilterString("F_WSL_EWM='"+code+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("PUR_ReceiveBill");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public JSONArray selectCheckJoinQty(String fEntryId) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("PUR_ReceiveBill");
        executeBillQuery.setFieldKeys("FCheckJoinBaseQty");
        executeBillQuery.setFilterString("FDetailEntity_FEntryId = " + fEntryId);
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("PUR_ReceiveBill");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public JSONArray selectChecker(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_OPERATOR");
        executeBillQuery.setFieldKeys("FStaffId.FNumber,FStaffId.FName");
        executeBillQuery.setFilterString("FOperatorType = 'ZJY'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_OPERATOR");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public JSONArray selectCheck_List(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("QM_InspectBill");
        executeBillQuery.setFieldKeys("F_JH");
        executeBillQuery.setFilterString("F_TXM <> ' ' and SUBSTRING(F_TXM, 1, CHARINDEX('-', F_TXM) - 1) = '"+code+"'");
        executeBillQuery.setOrderString("CONVERT(int,F_JH) desc");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(1);
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public JSONArray selectBPCheck_List(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("QM_InspectBill");
        executeBillQuery.setFieldKeys("FBillNo,FDocumentStatus,FMaterialId.FNumber,FMaterialId.FName,F_BH2,FDescription,FDate," +
                "FMaterialModel,FSupplierId.FName,FSrcBillNo0,FID,FInspectOrgId.FNumber,FBusinessType,FUnitID.FNumber,fSupplierId.FNumber," +
                "FEntity_Link_FSBillId,FEntity_Link_FSId");
        executeBillQuery.setFilterString("F_BPJY = 1 and FSEQ = 1");
        executeBillQuery.setOrderString("FBillNo desc");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(1000);
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public JSONArray selectBPCheck_ListDetails(String FBillNo) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("QM_InspectBill");
        executeBillQuery.setFieldKeys("F_MS2,F_JH,F_TXM,F_WTMS");
        executeBillQuery.setFilterString("FBillNo = '"+FBillNo+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }
    public JSONObject submitBill(String FBillNo) {
        Submit submit=new Submit();
        submit.setNumbers(Collections.singletonList(FBillNo));
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(submit);
        String barCodeJson = kingdeeApi.kingdeeSubmit(form);
        JSONObject jaData = JSON.parseObject(barCodeJson);
        return jaData;
    }
    public JSONObject auditBill(String FBillNo) {
        Submit submit=new Submit();
        submit.setNumbers(Collections.singletonList(FBillNo));
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(submit);
        String barCodeJson = kingdeeApi.kingdeeAudit(form);
        JSONObject jaData = JSON.parseObject(barCodeJson);
        return jaData;
    }
    public JSONObject unAuditBill(String FBillNo) {
        Submit submit=new Submit();
        submit.setNumbers(Collections.singletonList(FBillNo));
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(submit);
        String barCodeJson = kingdeeApi.kingdeeUnAudit(form);
        JSONObject jaData = JSON.parseObject(barCodeJson);
        return jaData;
    }
    public JSONObject deleteBill(String FBillNo) {
        Submit submit=new Submit();
        submit.setNumbers(Collections.singletonList(FBillNo));
        Form form =new Form();
        form.setFormid("QM_InspectBill");
        form.setData(submit);
        String barCodeJson = kingdeeApi.kingdeeDelete(form);
        JSONObject jaData = JSON.parseObject(barCodeJson);
        return jaData;
    }


    public  String selectBD_Supplier(String name, String orgid, Fishuform fishuform){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Supplier");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"' and FUseOrgId.FNumber='100' and FForbidStatus='A'");//+fishuform.getPayAccountFnumber()
        //executeBillQuery.setFilterString("FName='"+name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Supplier");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
//        try{
//
//
//          FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
//        }catch(Exception e){
//            log.info("获取供应商信息出现异常"+e.toString());
//
//        }

        if(JSON.parseArray(result).size()==0){
            FNumber=   kingdeeFormSaveSoso.ssaSupplier(orgid,name,fishuform);


        }else{

            FNumber = selectBD_SupplierUseOrg(name, orgid, fishuform);
        }

        return FNumber;

    }
    /**
     * @param
     * @param
     * @returns
     * @description 查询对应组织下未禁用的供应商名称是否存在
     */
    public  String selectBD_SupplierUseOrg(String name, String orgid, Fishuform fishuform){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Supplier");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"' and FUseOrgId.FNumber='"+fishuform.getPayAccountFnumber()+"' and FForbidStatus='A'");//+
        //executeBillQuery.setFilterString("FName='"+name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Supplier");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();

        if(JSON.parseArray(result).size()==0){
            log.error("组织："+fishuform.getPayAccountFnumber()+"下不存在供应商："+name);
        }else{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }

        return FNumber;

    }
    public  String selectExpense(String name){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Expense");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Expense");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        try{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }catch(Exception e){
            log.error("获取费用项目出现异常"+e.toString());

        }

        return FNumber;

    }

    public  String selectOrgnization(String name){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("ORG_Organizations");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("ORG_Organizations");
        form.setData(executeBillQuery);



        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        try{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }catch(Exception e){
            log.error("获取组织信息出现异常"+e.toString());

        }

        return FNumber;

    }
    public  String selectDepartment(String fishuId){
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("k2dc49b3f0a4544a1b6198c7b87b9db0a");
        executeBillQuery.setFieldKeys("FKDNumber,FBillNo,FFsDeptName,F_TSLY_FeishuId,FKdDeptName");
        executeBillQuery.setFilterString("FBillNo='NY001' and F_TSLY_FeishuId='"+fishuId+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("k2dc49b3f0a4544a1b6198c7b87b9db0a");
        form.setData(executeBillQuery);



        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        try{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }catch(Exception e){
            log.error("获取部门信息出现异常"+e.toString()+e.getStackTrace());

        }

        return FNumber;


    }
    public  String selectFapiao(String fishuname){
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("k2dc49b3f0a4544a1b6198c7b87b9db0a");
        executeBillQuery.setFieldKeys("FKDNumber,FBillNo,FFsDeptName,F_TSLY_FeishuId,FKdDeptName");
        executeBillQuery.setFilterString("FBillNo='NY002' and FFsDeptName='"+fishuname+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        Form form =new Form();
        form.setFormid("k2dc49b3f0a4544a1b6198c7b87b9db0a");
        form.setData(executeBillQuery);



        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        try{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }catch(Exception e){
            log.error("获取发票信息出现异常"+e.toString()+e.getStackTrace());

        }

        return FNumber;


    }

    public  String selectEmpinfo(String name,String orgid,Fishuform fishuform){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Customer");//客户 BD_Customer    员工  BD_Empinfo
        executeBillQuery.setFieldKeys("FNumber");
        // executeBillQuery.setFilterString("FName='"+name+"'"+fishuform.getPayAccountFnumber());
        executeBillQuery.setFilterString("FName='"+name+"' and FUseOrgId.FNumber='100' and FForbidStatus='A'");//+fishuform.getPayAccountFnumber()
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Customer");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
//        try{
//            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
//        }catch(Exception e){
//            log.info("获取客户信息出现异常"+e.toString());
//
//        }
        if(JSON.parseArray(result).size()==0){
            FNumber=   kingdeeFormSaveSoso.ssaCustomber(orgid,name,fishuform);


        }
        else
        {

            FNumber = selectEmpinfoUseOrg(name,orgid,fishuform);
//            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }

        return FNumber;




    }

    /**
     * @param
     * @param
     * @returns
     * @description 查询对应组织下未禁用的客户名称是否存在
     */
    public  String selectEmpinfoUseOrg(String name,String orgid,Fishuform fishuform){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Customer");//客户 BD_Customer    员工  BD_Empinfo
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"' and FUseOrgId.FNumber='"+fishuform.getPayAccountFnumber()+"' and FForbidStatus='A'");//+fishuform.getPayAccountFnumber()
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Customer");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        if(JSON.parseArray(result).size()==0){
            log.error("组织："+fishuform.getPayAccountFnumber()+"下不存在客户："+name);
        }else{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }
        return FNumber;
    }


    public  String selectCurrency(String name){

        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_Currency");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName='"+name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_Currency");
        form.setData(executeBillQuery);


        String result=kingdeeApi.kingdeeExecuteBillQuery(form);
        String FNumber=new String();
        try{
            FNumber = JSON.parseArray(result).getJSONArray(0).getString(0);
        }catch(Exception e){
            log.info("获取币别出现异常"+e.toString());

        }

        return FNumber;

    }

}
