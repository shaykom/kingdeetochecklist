package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.UserDepartment;
import com.chenmasoft.kingdeetofeishu.dao.service.UserDepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (UserDepartment)表控制层
 *
 * @author makejava
 * @since 2022-07-22 13:59:56
 */
@RestController
@RequestMapping("userDepartment")
public class UserDepartmentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserDepartmentService userDepartmentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param userDepartment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserDepartment> page, UserDepartment userDepartment) {
        return success(this.userDepartmentService.page(page, new QueryWrapper<>(userDepartment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userDepartmentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userDepartment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserDepartment userDepartment) {
        return success(this.userDepartmentService.save(userDepartment));
    }

    /**
     * 修改数据
     *
     * @param userDepartment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserDepartment userDepartment) {
        return success(this.userDepartmentService.updateById(userDepartment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userDepartmentService.removeByIds(idList));
    }
}

