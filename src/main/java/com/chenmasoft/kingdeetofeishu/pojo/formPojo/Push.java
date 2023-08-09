package com.chenmasoft.kingdeetofeishu.pojo.formPojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Push {
    /**
     * ids  2.1.Ids：单据内码集合，字符串类型，格式："Id1,Id2,..."（使用内码时必录）
     *      2.2.Numbers：单据编码集合，数组类型，格式：[No1,No2,...]（使用编码时必录）
     *      2.3.EntryIds：分录内码集合，逗号分隔（分录下推时必录） 注（按分录下推时，单据内码和编码不需要填,否则按整单下推）
     *      2.4.RuleId：转换规则内码，字符串类型（未启用默认转换规则时，则必录）
     *      2.5.TargetBillTypeId：目标单据类型内码，字符串类型（非必录）
     *      2.6.TargetOrgId：目标组织内码，整型（非必录）
     *      2.7.TargetFormId：目标单据FormId，字符串类型，（启用默认转换规则时，则必录）
     *      2.8.IsEnableDefaultRule：是否启用默认转换规则，布尔类型，默认false（非必录）
     *      2.9.IsDraftWhenSaveFail：保存失败时是否暂存，布尔类型，默认false（非必录）  注（暂存的单据是没有编码的）
     *      2.10.CustomParams：自定义参数，字典类型，格式："{key1:value1,key2:value2,...}"（非必录）  注（传到转换插件的操作选项中，平台不会解析里面的值）
     */


    /**
     * ids
     */
    @JSONField(name = "Ids",ordinal=0)
    private String ids;
    /**
     * numbers
     */
    @JSONField(name = "Numbers",ordinal=2)
    private List<String> numbers;
    /**
     * entryIds
     */
    @JSONField(name = "EntryIds",ordinal=4)
    private String entryIds;
    /**
     * ruleId
     */
    @JSONField(name = "RuleId",ordinal=6)
    private String ruleId;
    /**
     * targetBillTypeId
     */
    @JSONField(name = "TargetBillTypeId",ordinal=8)
    private String targetBillTypeId;
    /**
     * targetOrgId
     */
    @JSONField(name = "TargetOrgId",ordinal=10)
    private Integer targetOrgId;
    /**
     * targetFormId
     */
    @JSONField(name = "TargetFormId",ordinal=12)
    private String targetFormId;
    /**
     * isEnableDefaultRule
     */
    @JSONField(name = "IsEnableDefaultRule",ordinal=14)
    private String isEnableDefaultRule;
    /**
     * isDraftWhenSaveFail
     */
    @JSONField(name = "IsDraftWhenSaveFail",ordinal=16)
    private String isDraftWhenSaveFail;
    /**
     * customParams
     */
    @JSONField(name = "CustomParams",ordinal=18)
    private CustomParamsEntity customParams;
}
