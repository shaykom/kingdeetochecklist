package com.chenmasoft.kingdeetofeishu.pojo.formPojo.resultKingdeeMsg;

@lombok.NoArgsConstructor
@lombok.Data
public class KingdeeResultJson {
    /**
     * result
     */
    @com.alibaba.fastjson.annotation.JSONField(name = "Result")
    private com.chenmasoft.kingdeetofeishu.pojo.formPojo.resultKingdeeMsg.ResultAccountMsg result;
}
