package com.jite.pay.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/** 订单金额 **/
public class Amount {
    /**
     * 总金额
     */
    private BigDecimal total;

    /**
     * 货币类型
     */
    private String currency;

    /**
     * 用户支付币种
     */
    @JSONField(name = "payer_currency")
    private String payerCurrency;

    /**
     * 用户支付金额
     */
    @JSONField(name = "payer_total")
    private BigDecimal payerTotal;

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

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public void setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
    }

    public BigDecimal getPayerTotal() {
        return payerTotal;
    }

    public void setPayerTotal(BigDecimal payerTotal) {
        this.payerTotal = payerTotal;
    }
}
