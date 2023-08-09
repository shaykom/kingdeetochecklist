package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.Invoice;
import com.chenmasoft.kingdeetofeishu.dao.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Invoice)表控制层
 *
 * @author makejava
 * @since 2022-07-21 09:22:02
 */
@RestController
@RequestMapping("invoice")
public class InvoiceController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private InvoiceService invoiceService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param invoice 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Invoice> page, Invoice invoice) {
        return success(this.invoiceService.page(page, new QueryWrapper<>(invoice)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.invoiceService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param invoice 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Invoice invoice) {
        return success(this.invoiceService.save(invoice));
    }

    /**
     * 修改数据
     *
     * @param invoice 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Invoice invoice) {
        return success(this.invoiceService.updateById(invoice));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.invoiceService.removeByIds(idList));
    }
}

