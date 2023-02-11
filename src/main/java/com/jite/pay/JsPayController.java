package com.jite.pay;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.model.entity.*;
import com.jite.pay.model.request.*;
import com.jite.pay.service.*;

import java.math.BigDecimal;

public class JsPayController {

    /** 商户号 */
    public static String merchantId = "";
    /** 商户API公钥 */
    public static String publicKey = "";
    /** 商户API私钥 */
    public static String privateKey = "";
    /** 商户证书序列号 */
    public static String merchantSerialNumber = "";
    /** 商户API密钥 */
    public static String apikey = "";

    public static void main(String[] args) {
        Config config = new Config.Builder()
                .merchantId(merchantId)
                .serialNumber(merchantSerialNumber)
                .publicKey(publicKey)
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        TransferService service = new TransferService.Builder().config(config).build();

        TransferRequest request = new TransferRequest();
        request.setMchid("");
        request.setAppid("");
        request.setOutTradeNo("");
        request.setRemark("test");
        request.setAmount(new BigDecimal(""));
        request.setNotifyUrl("https://api.jitepay.com/v1/test");

        Payee payee = new Payee();
        payee.setIdentity("");
        payee.setIdentityType("BANKCARD_ACCOUNT");
        payee.setName("");
        payee.setAccountType(2);
        payee.setBankName("");
        payee.setBankBranchName("");
        payee.setBankProvince("");
        payee.setBankCity("");
        request.setPayee(payee);

        System.out.println(JSONObject.toJSONString(service.create(request)));
//        Config config = new Config.Builder()
//                .merchantId(merchantId)
//                .serialNumber(merchantSerialNumber)
//                .privateKey(privateKey)
//                .apiKey(apikey)
//                .build();
//
//        RefundService service = new RefundService.Builder().config(config).build();
//
//        RefundOrderRequest refund = new RefundOrderRequest();
//        refund.setOutTradeNo("");
//        refund.setOutRefundNo("");
//        refund.setReason("退款");
//
//        RefundAmount amount = new RefundAmount();
//        amount.setRefund(BigDecimal.valueOf());
//        amount.setTotal(BigDecimal.valueOf());
//        amount.setCurrency("CNY");
//        refund.setAmount(amount);
//        System.out.println(JSONObject.toJSONString(service.create(refund)));
    }
}