package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.ExpenseDepartment;
import com.chenmasoft.kingdeetofeishu.dao.service.ExpenseDepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (ExpenseDepartment)表控制层
 *
 * @author makejava
 * @since 2022-07-21 09:22:51
 */
@RestController
@RequestMapping("expenseDepartment")
public class ExpenseDepartmentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ExpenseDepartmentService expenseDepartmentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param expenseDepartment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<ExpenseDepartment> page, ExpenseDepartment expenseDepartment) {
        return success(this.expenseDepartmentService.page(page, new QueryWrapper<>(expenseDepartment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.expenseDepartmentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param expenseDepartment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ExpenseDepartment expenseDepartment) {
        return success(this.expenseDepartmentService.save(expenseDepartment));
    }

    /**
     * 修改数据
     *
     * @param expenseDepartment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ExpenseDepartment expenseDepartment) {
        return success(this.expenseDepartmentService.updateById(expenseDepartment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.expenseDepartmentService.removeByIds(idList));
    }
}

