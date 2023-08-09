package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew;


import java.util.Date;

public  class TokenResult {


    /**
     * code
     */
    private static Integer code;
    /**
     * expire
     */
    private static  Integer expire;
    /**
     * msg
     */
    private  static String msg;
    /**
     * tenant_access_token
     */
    private static  String tenant_access_token;

    private static Date logintime;

    public static Date getLogintime() {
        return logintime;
    }

    public static void setLogintime(Date logintime) {
        TokenResult.logintime = logintime;
    }

    public static void setCode(Integer code) {
        TokenResult.code = code;
    }

    public static void setExpire(Integer expire) {
        TokenResult.expire = expire;
    }

    public static void setMsg(String msg) {
        TokenResult.msg = msg;
    }

    public static void setTenant_access_token(String tenant_access_token) {
        TokenResult.tenant_access_token = tenant_access_token;
    }

    public static Integer getCode() {
        return code;
    }

    public static Integer getExpire() {
        return expire;
    }

    public static String getMsg() {
        return msg;
    }

    public static String getTenant_access_token() {
        return tenant_access_token;
    }
}
