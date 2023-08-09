package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuformentry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Fishuformentry)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-18 19:03:15
 */
@Mapper
@Repository
public interface FishuformentryDao extends BaseMapper<Fishuformentry> {

}

