package com.jite.pay.model.entity;

//场景信息
public class SceneInfo {
    private String payer_client_ip;//用户终端IP

    private String device_id;//商户端设备号

    private String type;//支付方式不能为空

    public String getPayer_client_ip() {
        return payer_client_ip;
    }

    public void setPayer_client_ip(String payer_client_ip) {
        this.payer_client_ip = payer_client_ip;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
