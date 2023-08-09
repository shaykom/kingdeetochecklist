package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * (Fishuform)表控制层
 *
 * @author makejava
 * @since 2022-07-18 19:03:23
 */
@RestController
@RequestMapping("index/fishuform")
public class FishuformController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private FishuformService fishuformService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param fishuform 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Fishuform> page, Fishuform fishuform) {
        return success(this.fishuformService.page(page, new QueryWrapper<>(fishuform)));
    }

    @GetMapping("fishuformDESC")
    public R selectAllByDesc() {

        QueryWrapper<Fishuform> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("end_time");





        return success(this.fishuformService.list(wrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.fishuformService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fishuform 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Fishuform fishuform) {
        return success(this.fishuformService.save(fishuform));
    }

    /**
     * 修改数据
     *
     * @param fishuform 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Fishuform fishuform) {
        return success(this.fishuformService.updateById(fishuform));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.fishuformService.removeByIds(idList));
    }

    @RequestMapping("/login")
    public String index() {
        return "login";
    }

    @RequestMapping("/toPage")//, method = RequestMethod.GET)
    public synchronized String toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        return url;
    }
}

