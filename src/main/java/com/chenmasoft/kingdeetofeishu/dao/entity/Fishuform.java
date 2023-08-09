package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Fishuform)表实体类
 *
 * @author makejava
 * @since 2022-07-18 19:02:25
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class Fishuform  {
    @TableId(value = "id")
    private String id;
    @TableField(value = "instance_code")
    private String instanceCode;
    @TableField(value = "approval_code")
    private String approvalCode;
    @TableField(value = "approval_name")
    private String approvalName;
    //部门id
    @TableField(value = "department_id")
    private String departmentId;
    @TableField(value = "status")
    private String status;
    @TableField(value = "serial_number")
    private String serialNumber;
    @TableField(value = "start_tim")
    private String startTim;
    @TableField(value = "end_time")
    private String endTime;
    @TableField(value = "uuid")
    private String uuid;
    @TableField(value = "user_id")
    private String userId;
    //部门
    @TableField(value = "department")
    private String department;
    //付款账户名称
    @TableField(value = "payAccount")
    private String payaccount;
    //收款账户账号
    @TableField(value = "recAccount")
    private String recaccount;
    //收款账户名称
    @TableField(value = "AccountName")
    private String accountname;
    //付款类型
    @TableField(value = "payType")
    private String paytype;


    @TableField(value = "isupload")
    private String isupload;

    @TableField(value = "uploadSuccess")
    private String uploadSuccess;




    @TableField(value = "payAccountFnumber")
    private String payAccountFnumber;
    @TableField(value = "AccountNameNumber")
    private String AccountNameNumber;
    @TableField(value = "fremark")
    private String fremark;
    @TableField(value = "departmentName")
    private String departmentName;
    @TableField(value = "departmentOpen_id")
    private String departmentOpen_id;
    @TableField(value = "kingdeeDepartment")
    private String kingdeeDepartment;

    @TableField(value = "currency")
    private String currency;
    @TableField(value = "currencyFnumber")
    private String currencyFnumber;
    @TableField(value = "isokpaybill")
    private String isokpaybill;
    @TableField(value = "isokpayable")
    private String isokpayable;
    @TableField(value = "isokotherpayable")
    private String isokotherpayable;
    }

