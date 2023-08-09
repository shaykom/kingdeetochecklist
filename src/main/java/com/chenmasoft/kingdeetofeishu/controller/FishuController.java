package com.chenmasoft.kingdeetofeishu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.chenmasoft.kingdeetofeishu.service.FishuEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableAsync
public class FishuController {
    @Autowired
    private FishuEvent fishuEvent;
    @PostMapping("/fishu")
    public String eventData(@RequestBody String s) throws InterruptedException {
        log.error(" url/fishu收到接口访问数据"+s);
        if(s==null){
            return null;
        }

    //在此进行逻辑处理
       JSONObject jsonpObject= JSON.parseObject(s);
        fishuEvent.getEvent(jsonpObject);





        log.error("此时代表已经成功回调"+s);
        return s;
    }






}
