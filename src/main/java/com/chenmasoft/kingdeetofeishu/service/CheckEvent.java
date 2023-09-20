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
public class CheckEvent {
}
