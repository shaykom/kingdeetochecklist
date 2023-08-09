package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Logmsgform;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Logmsgform)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-30 22:17:12
 */
@Mapper
@Repository
public interface LogmsgformDao extends BaseMapper<Logmsgform> {

}

