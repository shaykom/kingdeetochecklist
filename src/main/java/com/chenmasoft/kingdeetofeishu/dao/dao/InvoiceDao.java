package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Invoice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Invoice)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-21 09:22:02
 */
@Mapper
@Repository
public interface InvoiceDao extends BaseMapper<Invoice> {

}

