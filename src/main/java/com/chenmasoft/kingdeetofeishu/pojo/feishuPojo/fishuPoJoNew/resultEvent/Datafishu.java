package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo.fishuPoJoNew.resultEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Datafishu
 */
@NoArgsConstructor
@Data
public class Datafishu {
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
    private Long end_time;
    /**
     * form
     */
    private String form;
    /**
     * open_id
     */
    private String open_id;
    /**
     * reverted
     */
    private Boolean reverted;
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
    private List<TaskListfishu> task_list;
    /**
     * timeline
     */
    private List<Timelinefishu> timeline;
    /**
     * user_id
     */
    private String user_id;
    /**
     * uuid
     */
    private String uuid;
}
