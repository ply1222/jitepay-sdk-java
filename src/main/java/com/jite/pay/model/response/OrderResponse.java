package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.jite.pay.model.entity.Amount;
import com.jite.pay.model.entity.Payer;
import com.jite.pay.model.entity.SceneInfo;

/** 订单 **/
public class OrderResponse {
    /**
     * 交易号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;

    /**
     * 交易状态
     */
    @JSONField(name = "trade_state")
    private String tradeState;

    /**
     * 交易状态描述
     */
    @JSONField(name = "trade_state")
    private String tradeStateDesc;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 支付完成时间
     */
    @JSONField(name = "success_time")
    private String successTime;

    /**
     * 支付者
     */
    private Payer payer;

    /**
     * 订单金额
     */
    private Amount amount;

    /**
     * 场景信息
     */
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }
}
