package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Fishuform)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-18 19:02:22
 */
@Mapper
@Repository
public interface FishuformDao extends BaseMapper<Fishuform> {

}

