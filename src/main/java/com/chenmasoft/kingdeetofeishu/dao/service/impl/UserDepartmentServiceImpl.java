package com.chenmasoft.kingdeetofeishu.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenmasoft.kingdeetofeishu.dao.dao.UserDepartmentDao;
import com.chenmasoft.kingdeetofeishu.dao.entity.UserDepartment;
import com.chenmasoft.kingdeetofeishu.dao.service.UserDepartmentService;
import org.springframework.stereotype.Service;

/**
 * (UserDepartment)表服务实现类
 *
 * @author makejava
 * @since 2022-07-22 13:59:56
 */
@Service("userDepartmentService")
public class UserDepartmentServiceImpl extends ServiceImpl<UserDepartmentDao, UserDepartment> implements UserDepartmentService {

}

