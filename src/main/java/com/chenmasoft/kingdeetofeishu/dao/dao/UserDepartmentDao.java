package com.chenmasoft.kingdeetofeishu.dao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmasoft.kingdeetofeishu.dao.entity.UserDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (UserDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-22 13:59:56
 */
@Mapper
@Repository
public interface UserDepartmentDao extends BaseMapper<UserDepartment> {

}

