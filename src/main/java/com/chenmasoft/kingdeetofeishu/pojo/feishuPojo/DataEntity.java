package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DataEntity
 */
@NoArgsConstructor
@Data
public class DataEntity {
    /**
     * approval_code
     */
    private String approval_code;
    /**
     * approval_name
     */
    private String approval_name;
    /**
     * comment_list
     */
    private List<?> comment_list;
    /**
     * department_id
     */
    private String department_id;
    /**
     * end_time
     */
    private Integer end_time;
    /**
     * form
     */
    private String form;
    /**
     * open_id
     */
    private String open_id;
    /**
     * serial_number
     */
    private String serial_number;
    /**
     * start_time
     */
    private Long start_time;
    /**
     * status
     */
    private String status;
    /**
     * task_list
     */
    private List<TaskListEntity> task_list;
    /**
     * timeline
     */
    private List<TimelineEntity> timeline;
    /**
     * user_id
     */
    private String user_id;
    /**
     * uuid
     */
    private String uuid;
}
