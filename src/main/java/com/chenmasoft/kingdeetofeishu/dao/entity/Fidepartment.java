package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Fidepartment)表实体类
 *
 * @author makejava
 * @since 2022-07-22 15:16:49
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class Fidepartment {
    @TableId("id")
    private String id;
    @TableField(value = "fishuDepartmentname")
    private String fishudepartmentname;
    @TableField(value = "kingdeeDepartmentname")
    private String kingdeedepartmentname;
    @TableField(value = "kingdeeDepartmentnumber")
    private String kingdeedepartmentnumber;


    }

