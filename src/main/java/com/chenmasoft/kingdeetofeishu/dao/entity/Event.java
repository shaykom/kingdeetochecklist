package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Event)表实体类
 *
 * @author makejava
 * @since 2022-07-18 19:17:52
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
public class Event  {
    @TableField(value = "approval_code")
    private String approvalCode;
    @TableField(value = "tenant_key")
    private String tenantKey;
    @TableId("instance_code")
    private String instanceCode;
    @TableField(value = "type")
    private String type;
    @TableField(value = "app_id")
    private String appId;
    @TableField(value = "uuid")
    private String uuid;
    @TableField(value = "operate_time")
    private String operateTime;
    @TableField(value = "status")
    private String status;
    @TableField(value = "isuse")
    private Integer isuse;



    }

