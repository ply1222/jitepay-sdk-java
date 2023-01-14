package com.jite.pay.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

/** 场景信息 **/
public class SceneInfo {
    /**
     * 用户终端IP
     */
    @JSONField(name = "payer_client_ip")
    private String payerClientIp;

    public String getPayerClientIp() {
        return payerClientIp;
    }

    public void setPayerClientIp(String payerClientIp) {
        this.payerClientIp = payerClientIp;
    }
}
