package com.jite.pay.model.entity;

import java.math.BigDecimal;

public class GoodsDetail {
    private String merchant_goods_id;//商户侧商品编码

    private String goods_name;//商品名称

    private BigDecimal unit_price;//商品单价

    private BigDecimal refund_amount;//商品退款金额

    private Integer refund_quantity;//商品退货数量

    public String getMerchant_goods_id() {
        return merchant_goods_id;
    }

    public void setMerchant_goods_id(String merchant_goods_id) {
        this.merchant_goods_id = merchant_goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(BigDecimal refund_amount) {
        this.refund_amount = refund_amount;
    }

    public Integer getRefund_quantity() {
        return refund_quantity;
    }

    public void setRefund_quantity(Integer refund_quantity) {
        this.refund_quantity = refund_quantity;
    }

}
