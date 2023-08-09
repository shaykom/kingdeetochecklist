package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Event)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-18 19:17:52
 */
@Mapper
@Repository
public interface EventDao extends BaseMapper<Event> {

}

