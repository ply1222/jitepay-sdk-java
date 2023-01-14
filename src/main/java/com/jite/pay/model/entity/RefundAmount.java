package com.jite.pay.model.entity;

import java.math.BigDecimal;

/** 金额信息 */
public class RefundAmount {
    /**
     * 退款金额
     */
    private BigDecimal refund;

    /**
     * 原订单金额
     */
    private BigDecimal total;

    /**
     * 退款币种
     */
    private String currency;

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
