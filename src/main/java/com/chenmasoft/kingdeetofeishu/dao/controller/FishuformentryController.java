package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuformentry;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformentryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Fishuformentry)表控制层
 *
 * @author makejava
 * @since 2022-07-18 19:03:14
 */
@RestController
@RequestMapping("index/fishuformentry")
public class FishuformentryController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private FishuformentryService fishuformentryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fishuformentry 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Fishuformentry> page, Fishuformentry fishuformentry) {
        return success(this.fishuformentryService.page(page, new QueryWrapper<>(fishuformentry)));
    }
    @GetMapping("fishuformentryDESC")
    public R selectAllByDesc() {

        QueryWrapper<Fishuformentry> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");





        return success(this.fishuformentryService.list(wrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.fishuformentryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fishuformentry 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Fishuformentry fishuformentry) {
        return success(this.fishuformentryService.save(fishuformentry));
    }

    /**
     * 修改数据
     *
     * @param fishuformentry 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Fishuformentry fishuformentry) {
        return success(this.fishuformentryService.updateById(fishuformentry));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.fishuformentryService.removeByIds(idList));
    }
}

