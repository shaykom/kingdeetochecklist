package com.chenmasoft.kingdeetofeishu.pojo.formPojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Submit  {
    //提交的json实体类


    /**
     * createOrgId
     */
    @JSONField(name = "CreateOrgId",ordinal = 0)
    private Integer createOrgId;
    /**
     * numbers
     */
    @JSONField(name = "Numbers",ordinal = 1)
    private List<String> numbers;
    /**
     * ids
     */
    @JSONField(name = "Ids",ordinal = 4)
    private String ids;
    /**
     * selectedPostId
     */
    @JSONField(name = "SelectedPostId",ordinal = 6)
    private Integer selectedPostId;
    /**
     * networkCtrl
     */
    @JSONField(name = "NetworkCtrl",ordinal = 9)
    private String networkCtrl;
}
