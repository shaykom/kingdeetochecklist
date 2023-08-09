package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Logmsgform)表实体类
 *
 * @author makejava
 * @since 2022-07-30 22:17:13
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class Logmsgform  {
    @TableId("id")
    private String id;
    //简略的日志消息
    @TableField(value = "loginfoMsg")
    private String loginfomsg;
    //日志产生时间
    @TableField(value = "logdatetime")
    private String logdatetime;
    //飞书的唯一code
    @TableField(value = "feishu_instanscode")
    private String feishuInstanscode;
    //流程类型
    @TableField(value = "approvecode")
    private String approvecode;
    //流程类型名称
    @TableField(value = "approvecodeName")
    private String approvecodename;
    //详细的日志
    @TableField(value = "xiangxideLog")
    private String xiangxidelog;


    }

