package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.ExpenseDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (ExpenseDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-21 09:22:51
 */
@Mapper
@Repository
public interface ExpenseDepartmentDao extends BaseMapper<ExpenseDepartment> {

}

