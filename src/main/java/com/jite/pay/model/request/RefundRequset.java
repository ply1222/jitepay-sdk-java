package com.jite.pay.model.request;

import com.jite.pay.model.entity.Amount;
import com.jite.pay.model.entity.GoodsDetail;

public class RefundRequset {
    private String out_trade_no;//商户订单号

    private String out_refund_no;//商户退款单号

    private String reason;//退款原因

    private String notify_url;//通知回调地址

    private String funds_account;//退款资金来源

    private Amount amount;//金额信息

    private GoodsDetail goods_detail;//退款商品

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getFunds_account() {
        return funds_account;
    }

    public void setFunds_account(String funds_account) {
        this.funds_account = funds_account;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public GoodsDetail getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(GoodsDetail goods_detail) {
        this.goods_detail = goods_detail;
    }
}
