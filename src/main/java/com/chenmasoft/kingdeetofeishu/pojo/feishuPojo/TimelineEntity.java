package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TimelineEntity
 */
@NoArgsConstructor
@Data
public class TimelineEntity {
    /**
     * create_time
     */
    private Long create_time;
    /**
     * ext
     */
    private String ext;
    /**
     * open_id
     */
    private String open_id;
    /**
     * type
     */
    private String type;
    /**
     * user_id
     */
    private String user_id;
}
