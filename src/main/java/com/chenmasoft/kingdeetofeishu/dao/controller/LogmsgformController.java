package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.Logmsgform;
import com.chenmasoft.kingdeetofeishu.dao.service.LogmsgformService;
import com.chenmasoft.kingdeetofeishu.service.FishuEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Logmsgform)表控制层
 *
 * @author makejava
 * @since 2022-07-30 22:17:12
 */
@RestController
@RequestMapping("index/logmsgform")
public class LogmsgformController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LogmsgformService logmsgformService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param logmsgform 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Logmsgform> page, Logmsgform logmsgform) {
        return success(this.logmsgformService.page(page, new QueryWrapper<>(logmsgform)));
    }
    @GetMapping("logDESC")
    public R selectAllByDesc() {

        QueryWrapper<Logmsgform> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("logdatetime");





        return success(this.logmsgformService.list(wrapper));
    }
//    @GetMapping("logDESC2")
//    public R selectAllByDesc2() {
//
//        QueryWrapper<Logmsgform> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("logdatetime");
//
//
//
//
//
//        return success(this.logmsgformService.list(wrapper));
//    }
@Autowired
private FishuEvent fishuEvent;
@PostMapping("downloadQS")
public String downloadQS() {

    fishuEvent.getEvent2QueryList();

    return "操作成功";
}

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.logmsgformService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param logmsgform 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Logmsgform logmsgform) {
        return success(this.logmsgformService.save(logmsgform));
    }

    /**
     * 修改数据
     *
     * @param logmsgform 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Logmsgform logmsgform) {
        return success(this.logmsgformService.updateById(logmsgform));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.logmsgformService.removeByIds(idList));
    }
}

