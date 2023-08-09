package com.chenmasoft.kingdeetofeishu.pojo.feishuPojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountBankArea {
    /**
     * code
     */
    private String code;
    /**
     * name
     */
    private String name;
    /**
     * geoname_id
     */
    private Integer geoname_id;
    /**
     * asci_name
     */
    private String asci_name;
    /**
     * level
     */
    private String level;
}
