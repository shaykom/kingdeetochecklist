package com.chenmasoft.kingdeetofeishu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Invoice)表实体类
 *
 * @author makejava
 * @since 2022-07-21 09:22:02
 */
@Data
@NoArgsConstructor
public class Invoice  {
    @TableField(value = "fnumber")
    private String fnumber;
    @TableField(value = "k_name")
    private String kName;
    @TableField(value = "f_name")
    private String fName;
    @TableId("id")
    private String id;




    }

