package com.jite.pay.model.request;

import com.jite.pay.model.entity.Amount;
import com.jite.pay.model.entity.Payer;
import com.jite.pay.model.entity.SceneInfo;
import com.jite.pay.model.entity.SettleInfo;

//预处理
public class PrepayRequest {
    private String channel;//交易类型

    private String appid;//应用ID

    private String mchid;//商户号

    private String description;//商品描述

    private String out_trade_no;//商户订单号

    private String time_expire;//交易结束时间

    private String attach;//附加数据

    private String notify_url;//通知地址

    private Amount amount;//订单金额

    private Payer payer;//支付者

    private SceneInfo scene_info;//场景信息

    private SettleInfo settle_info;//结算信息

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public SceneInfo getScene_info() {
        return scene_info;
    }

    public void setScene_info(SceneInfo scene_info) {
        this.scene_info = scene_info;
    }

    public SettleInfo getSettle_info() {
        return settle_info;
    }

    public void setSettle_info(SettleInfo settle_info) {
        this.settle_info = settle_info;
    }
}
