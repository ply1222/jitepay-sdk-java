package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class JSAPIResponse {
    @JSONField(name = "prepay_id")
    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
