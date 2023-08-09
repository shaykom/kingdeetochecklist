package com.chenmasoft.kingdeetofeishu.apiRequst;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.GetToken;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.Token;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.TokenResult;
import com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.resultEvent.ResultFishu;
import com.chenmasoft.kingdeetofeishu.service.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class FeishuApi {
    @Value("${feishu.app_id}")
    private String app_id;
    @Value("${feishu.app_secret}")
    private String app_secret;
    @Autowired
    private LogUtil logUtil;

    public Token loginAs(){
        Token token=new Token();
        if(TokenResult.getTenant_access_token()==null || TokenResult.getLogintime().getTime()-new Date().getTime()>6600) {
            token= login();

        }
        else {
            token.setCode(TokenResult.getCode());
            token.setExpire(TokenResult.getExpire());
            token.setMsg(TokenResult.getMsg());
            token.setTenant_access_token(TokenResult.getTenant_access_token());

        }
        return   token;

    }

    public Token login(){

        GetToken getToken=new GetToken();
        getToken.setApp_id(app_id);
        getToken.setApp_secret(app_secret);

        String reternToken;

        try {
            HttpResponse result = HttpRequest.post("https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal")
                    .body(JSON.toJSONString(getToken)).execute();
            reternToken=result.body();
        }catch (Exception e){

            e.getMessage();
            log.error("飞书登陆失败，遇到异常");
            logUtil.saveLog("飞书登陆失败，遇到异常",null,null,null,e.getStackTrace()+e.getMessage());

            return null;
        }
        Token token= JSON.parseObject( reternToken, Token.class);
        TokenResult.setLogintime(new Date());

        return token;
    }

    //查询飞书实例api
    public  String singleEvnt(String instance_code){

        Token token=loginAs();
        HashMap<String,Object> inscodeObj=new HashMap<>();
        inscodeObj.put("instance_code",instance_code);
        HttpResponse   result = HttpRequest.post("https://www.feishu.cn/approval/openapi/v2/instance/get")
                .header("Authorization", "Bearer" + " " + token.getTenant_access_token()).body(JSON.toJSONString(inscodeObj)).execute();

        ResultFishu resultFishu =JSON.parseObject(result.body(),ResultFishu.class);
        if(resultFishu.getCode()==0){
            log.info("获取实例："+instance_code+"成功");
            return result.body();
        }
        else{
            log.error("获取实例："+instance_code+"失败"+resultFishu==null?"无响应信息":resultFishu.toString());
            logUtil.saveLog("获取实例："+instance_code+"失败"+resultFishu==null?"无响应信息":resultFishu.toString(),null,null,null,null);

            return null;
        }

    }
}

