package com.jite.pay.model.entity;

//结算信息
public class SettleInfo {

    private Boolean profit_sharing;//是否指定分账

    public Boolean getProfit_sharing() {
        return profit_sharing;
    }

    public void setProfit_sharing(Boolean profit_sharing) {
        this.profit_sharing = profit_sharing;
    }
}
