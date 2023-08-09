package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fidepartment;
import com.chenmasoft.kingdeetofeishu.dao.service.FidepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Fidepartment)表控制层
 *
 * @author makejava
 * @since 2022-07-22 15:16:49
 */
@RestController
@RequestMapping("fidepartment")
public class FidepartmentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private FidepartmentService fidepartmentService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fidepartment 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Fidepartment> page, Fidepartment fidepartment) {
        return success(this.fidepartmentService.page(page, new QueryWrapper<>(fidepartment)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.fidepartmentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fidepartment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Fidepartment fidepartment) {
        return success(this.fidepartmentService.save(fidepartment));
    }

    /**
     * 修改数据
     *
     * @param fidepartment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Fidepartment fidepartment) {
        return success(this.fidepartmentService.updateById(fidepartment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.fidepartmentService.removeByIds(idList));
    }
}

