package com.jite.pay.model.entity;


import java.math.BigDecimal;

//订单金额
public class Amount {
    private BigDecimal total;//总金额

    private BigDecimal refund;//退款金额

    private String currency;//货币类型

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
