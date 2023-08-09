package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Fishuformentry)表实体类
 *
 * @author makejava
 * @since 2022-07-18 19:03:15
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class Fishuformentry  {
    @TableId("id")
    private String id;
    @TableField(value = "instance_code")
    private String instanceCode;
    //费用部门
    @TableField(value = "payDepartment")
    private String paydepartment;
    //费用类型
    @TableField(value = "monyType")
    private String monytype;
    //发票
    @TableField(value = "fapiao")
    private String fapiao;
    //内容
    @TableField(value = "someThing")
    private String something;
    //付款金额（人民币元）
    @TableField(value = "money")
    private String money;
    @TableField(value = "payDepartmentNumber")
    private String payDepartmentNumber;
    @TableField(value = "fapiaoNumber")
    private String fapiaoNumber;
    @TableField(value = "monyTypeNumber")
    private String monyTypeNumber;
    //souceid
    @TableField(value = "souceid")
    private String souceid;


    @TableField(value = "currency")
    private String currency;

      // 币别对应的金蝶的编号
    @TableField(value = "currencyFnumber")
    private String currencyFnumber;
    }

