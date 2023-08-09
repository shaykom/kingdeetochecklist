package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TaskListEntity
 */
@NoArgsConstructor
@Data
public class TaskListEntity {
    /**
     * end_time
     */
    private Integer end_time;
    /**
     * id
     */
    private String id;
    /**
     * open_id
     */
    private String open_id;
    /**
     * start_time
     */
    private Long start_time;
    /**
     * status
     */
    private String status;
    /**
     * type
     */
    private String type;
    /**
     * user_id
     */
    private String user_id;
}
