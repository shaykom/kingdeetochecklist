package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fidepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Fidepartment)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-22 15:16:49
 */
@Mapper
@Repository
public interface FidepartmentDao extends BaseMapper<Fidepartment> {

}

