package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew;

import lombok.Data;

@Data
public class Token {
    /**
     * code
     */
    private  Integer code;
    /**
     * expire
     */
    private Integer expire;
    /**
     * msg
     */
    private   String msg;
    /**
     * tenant_access_token
     */
    private   String tenant_access_token;

}
