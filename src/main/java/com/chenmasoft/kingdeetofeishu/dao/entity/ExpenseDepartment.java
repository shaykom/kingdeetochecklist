package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (ExpenseDepartment)表实体类
 *
 * @author makejava
 * @since 2022-07-21 09:22:51
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class ExpenseDepartment {
    @TableField(value = "f_department")
    private String fDepartment;
    @TableField(value = "k_department")
    private String kDepartment;
    @TableField(value = "k_departmentfnumber")
    private String kDepartmentfnumber;
    @TableId("id")
    private String id;



    }

