package com.chenmasoft.kingdeetofeishu.dao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Event;
import com.chenmasoft.kingdeetofeishu.dao.service.EventService;
import com.chenmasoft.kingdeetofeishu.service.FishuEvent;
import com.chenmasoft.kingdeetofeishu.service.SaveTokingdee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ReloadController {
    @Autowired
    private SaveTokingdee saveTokingdee;
    @Autowired
    private EventService eventService;
    @Autowired
    private FishuEvent fishuEvent;

    @PostMapping("index/reload")
    public void reload(@RequestBody String s){
        System.out.println(s);
        String[] strlist = s.split("&");


        for (int i = 0; i <strlist.length ; i++) {

            String[] strlist2=   strlist[i].split("=");
            System.out.println(strlist2[1]);

            // 2.从数据库查询事件详情的实例，得到详情保存到数据库
            QueryWrapper<Event> eventQueryWrapper=new QueryWrapper<Event>()
                    .eq("status","APPROVED")
                    .eq("instance_code",strlist2[1]);


            Event event= eventService.getOne(eventQueryWrapper);
            if(event==null){
                return;
            }
            fishuEvent.saveFishuSome(event);// 存储事件到数据库

            saveTokingdee.selectFormByInsCode(strlist2[1]);
        }


    }

}
