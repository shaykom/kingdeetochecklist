package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FPaySubEntityEntity
 */
@NoArgsConstructor
@Data
public class FPaySubEntityEntity {
    /**
     * fDetailID
     */
    @JSONField(name = "FDetailID")
    private Integer fDetailID;
}
