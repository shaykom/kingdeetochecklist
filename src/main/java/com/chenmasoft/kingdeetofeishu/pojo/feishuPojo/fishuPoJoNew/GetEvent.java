package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetEvent {
    /**
     * event
     */
    private EventMsg event;
    /**
     * type
     */
    private String type;
    /**
     * uuid
     */
    private String uuid;
    /**
     * token
     */
    private String token;
    /**
     * ts
     */
    private String ts;
}
