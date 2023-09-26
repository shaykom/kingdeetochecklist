package com.chenmasoft.kingdeetofeishu.dao.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenmasoft.kingdeetofeishu.apiRequst.KingdeeApi;
import com.chenmasoft.kingdeetofeishu.dao.entity.CheckList;
import com.chenmasoft.kingdeetofeishu.dao.entity.Fishuform;
import com.chenmasoft.kingdeetofeishu.dao.service.FishuformService;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.ExecuteBillQuery;
import com.chenmasoft.kingdeetofeishu.pojo.formPojo.Form;
import com.chenmasoft.kingdeetofeishu.service.CheckEvent;
import com.chenmasoft.kingdeetofeishu.service.SaveTokingdee;
import com.chenmasoft.kingdeetofeishu.service.SelectForm;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("index/check")
public class CheckController extends ApiController {

    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private SelectForm selectForm;
    @Autowired
    private SaveTokingdee saveTokingdee;

    @GetMapping("barCode")
    public JSONArray selectBarCode(@RequestParam String param) {
        JSONArray jaData = selectForm.selectBar_Code(param);
        return jaData;
    }

    @GetMapping("checker")
    public JSONArray selectChecker(@RequestParam String param) {
        JSONArray jaData = selectForm.selectChecker(param);
        return jaData;
    }

    @GetMapping("selectCheckList")
    public JSONArray selectCheckList(@RequestParam String param) {
        JSONArray jaData = selectForm.selectCheck_List(param);
        return jaData;
    }

    @GetMapping("selectBPCheckList")
    public JSONArray selectBPCheckList(@RequestParam String param) {
        JSONArray jaData = selectForm.selectBPCheck_List(param);
        return jaData;
    }

    @GetMapping("selectBPCheckListDetails")
    public JSONArray selectBPCheckListDetails(@RequestParam String param) {
        JSONArray jaData = selectForm.selectBPCheck_ListDetails(param);
        return jaData;
    }

    @GetMapping("submitCheckList")
    public JSONObject submitCheckList(@RequestParam String param) {
        JSONObject jaData = selectForm.submitBill(param);
        return jaData;
    }

    @GetMapping("auditCheckList")
    public JSONObject auditCheckList(@RequestParam String param) {
        JSONObject jaData = selectForm.auditBill(param);
        return jaData;
    }

    @GetMapping("unAuditCheckList")
    public JSONObject unAuditCheckList(@RequestParam String param) {
        JSONObject jaData = selectForm.unAuditBill(param);
        return jaData;
    }

    @GetMapping("deleteCheckList")
    public JSONObject deleteCheckList(@RequestParam String param) {
        JSONObject jaData = selectForm.deleteBill(param);
        return jaData;
    }

    @PostMapping("checkList")
    public JSONObject saveKDCheckList(@RequestBody CheckList requestData) {
        JSONObject jaData = saveTokingdee.saveCheckList(requestData);
        return jaData;
    }
}

