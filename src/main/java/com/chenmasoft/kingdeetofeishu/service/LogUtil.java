package com.chenmasoft.kingdeetofeishu.service;

import com.chenmasoft.kingdeetofeishu.dao.entity.Logmsgform;
import com.chenmasoft.kingdeetofeishu.dao.service.LogmsgformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class LogUtil {

    @Autowired
    private LogmsgformService logmsgformService;
   public void saveLog(String msg,String feishuInstanscode,String Approvecode,String Approvecodename,String Xiangxidelog ){


       Logmsgform logmsgform=new Logmsgform();
       //logmsgform.setId("");
       logmsgform.setLoginfomsg(msg);
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

       logmsgform.setLogdatetime(simpleDateFormat.format(new Date().getTime()));
       logmsgform.setFeishuInstanscode(feishuInstanscode);
       logmsgform.setApprovecode(Approvecode);
       logmsgform.setApprovecodename(Approvecodename);
       logmsgform.setXiangxidelog(Xiangxidelog);


       logmsgformService.save(logmsgform);


   }
}
