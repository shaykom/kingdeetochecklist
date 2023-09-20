package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PurReceiveUpdateModel {
    /**
     * fid
     */
    @JSONField(name = "FID",ordinal = 0)
    private int fid;
    /**
     * fDetailEntity
     */
    @JSONField(name = "FDetailEntity",ordinal = 1)
    private List<PurReceiveUpdateEntity> fDetailEntity;
}
