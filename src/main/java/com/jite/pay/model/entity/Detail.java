package com.jite.pay.model.entity;


import java.math.BigDecimal;
import java.util.List;

/** 优惠功能 **/
public class Detail {
    /**
     * 订单原价
     */
    private BigDecimal costPrice;

    /**
     * 商品小票ID
     */
    private String invoiceId;

    /**
     * 单品列表
     */
    private List<GoodsDetail> goodsDetail;

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<GoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<GoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
