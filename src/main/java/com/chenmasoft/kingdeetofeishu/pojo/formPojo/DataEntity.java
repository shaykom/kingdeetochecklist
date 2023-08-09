package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DataEntity  {
    /**
     * needUpDateFields
     */
    @JSONField(name = "NeedUpDateFields",ordinal = 0)
    private List<String> needUpDateFields;
    /**
     * needReturnFields
     */
    @JSONField(name = "NeedReturnFields",ordinal = 1)
    private List<String> needReturnFields;
    /**
     * isDeleteEntry
     */
    @JSONField(name = "IsDeleteEntry",ordinal = 2)
    private String isDeleteEntry;
    /**
     * subSystemId
     */
    @JSONField(name = "SubSystemId",ordinal = 3)
    private String subSystemId;
    /**
     * isVerifyBaseDataField
     */
    @JSONField(name = "IsVerifyBaseDataField",ordinal = 4)
    private String isVerifyBaseDataField;
    /**
     * isEntryBatchFill
     */
    @JSONField(name = "IsEntryBatchFill",ordinal = 5)
    private String isEntryBatchFill;
    /**
     * validateFlag
     */
    @JSONField(name = "ValidateFlag",ordinal = 6)
    private String validateFlag;
    /**
     * numberSearch
     */
    @JSONField(name = "NumberSearch",ordinal = 7)
    private String numberSearch;
    /**
     * isAutoAdjustField
     */
    @JSONField(name = "IsAutoAdjustField",ordinal = 8)
    private String isAutoAdjustField;
    /**
     * interationFlags
     */
    @JSONField(name = "InterationFlags",ordinal = 9)
    private String interationFlags;
    /**
     * ignoreInterationFlag
     */
    @JSONField(name = "IgnoreInterationFlag",ordinal = 10)
    private String ignoreInterationFlag;
    /**
     * model
     */
    @JSONField(name = "Model",ordinal = 11)
    private Object model;
}
