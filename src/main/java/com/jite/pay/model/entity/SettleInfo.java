package com.jite.pay.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

/** 结算信息 **/
public class SettleInfo {
    /**
     * 是否指定分账
     */
    @JSONField(name = "profit_sharing")
    private Boolean profitSharing;

    public Boolean getProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(Boolean profitSharing) {
        this.profitSharing = profitSharing;
    }
}
