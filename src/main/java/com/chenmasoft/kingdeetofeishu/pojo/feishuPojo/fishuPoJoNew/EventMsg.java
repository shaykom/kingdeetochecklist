package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EventAccountMsg
 */
@NoArgsConstructor
@Data
public class EventMsg {
    /**
     * approval_code
     */
    private String approval_code;
    /**
     * tenant_key
     */
    private String tenant_key;
    /**
     * instance_code
     */
    private String instance_code;
    /**
     * type
     */
    private String type;
    /**
     * app_id
     */
    private String app_id;
    /**
     * uuid
     */
    private String uuid;
    /**
     * operate_time
     */
    private String operate_time;
    /**
     * status
     */
    private String status;
}
