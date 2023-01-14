package com.jite.pay.model.request;

import com.jite.pay.model.entity.*;

/** 预处理 **/
public class PrepayRequest {
    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 交易渠道
     */
    private String channel;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 交易结束时间
     */
    private String timeExpire;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 订单优惠标记
     */
    private String goodsTag;

    /**
     * 订单金额
     */
    private Amount amount;

    /**
     * 支付者
     */
    private Payer payer;

    /**
     * 优惠功能
     */
    private Detail detail;

    /**
     * 场景信息
     */
    private SceneInfo sceneInfo;

    /**
     * 结算信息
     */
    private SettleInfo settleInfo;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
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

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public SettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }
}
