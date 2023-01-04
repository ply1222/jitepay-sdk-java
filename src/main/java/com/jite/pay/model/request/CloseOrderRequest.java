package com.jite.pay.model.request;

//关闭订单
public class CloseOrderRequest {

    private String mchid;//商户号


    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

}
