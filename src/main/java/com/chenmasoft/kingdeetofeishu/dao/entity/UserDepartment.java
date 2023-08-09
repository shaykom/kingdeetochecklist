package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserDepartment)表实体类
 *
 * @author makejava
 * @since 2022-07-22 13:59:56
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class UserDepartment  {
    @TableId("f_userid")
    private String fUserid;
    @TableField(value = "f_name")
    private String fName;
    @TableField(value = "f_department")
    private String fDepartment;
    @TableField(value = "f_departmentid")
    private String fDepartmentid;
    @TableField(value = "k_fnumber")
    private String kFnumber;
    @TableField(value = "k_fname")
    private String kFname;
    @TableField(value = "k_userid")
    private String kUserid;



    }

