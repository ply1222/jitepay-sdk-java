package com.jite.pay.service;


import com.jite.pay.model.entity.*;
import com.jite.pay.model.request.*;
import com.jite.pay.util.RSAUtil;
import com.jite.pay.util.Transaction;

import java.io.IOException;
import java.math.BigDecimal;

public class JsapiService {


    public static void main(String[] args) throws IOException {
        JsapiService jsapiService = new JsapiService();
        System.out.println(111);
        jsapiService.queryBank();
    }

    public static void pay() throws IOException {
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(BigDecimal.valueOf(0.1));
        amount.setCurrency("CNY");
        request.setAmount(amount);
        request.setChannel("alipay_wap");
        request.setAppid("2");
        request.setMchid("2");
        request.setDescription("test");
        request.setNotify_url("https://api.jitepay.com/v1/test");
        request.setOut_trade_no("254635569865545626551423");
        Payer payer = new Payer();
        payer.setOpenid("");//os8zl4ipy1o6Qbvzzx2sOfmtKBKs
        request.setPayer(payer);
        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setPayer_client_ip("127.0.0.1");
        request.setScene_info(sceneInfo);
        System.out.println(Transaction.jsapi(request));
    }

    public static void query() throws IOException {
        QueryOrderByIdRequest queryRequest = new QueryOrderByIdRequest();
        queryRequest.setMchid("2");
        System.out.println(Transaction.queryOrder(queryRequest));
    }

//    public static void colse() {
//        CloseOrderRequest closeRequest = new CloseOrderRequest();
//        closeRequest.setMchid("2");
//    }

    public static void refundOrder() throws IOException {
        RefundRequset refundRequset = new RefundRequset();
        refundRequset.setOut_trade_no("254635569865545626551423");
        //DateFormatUtils.format(new Date(),"yyyyMMddHHmmss")+ RandomStringUtils.randomNumeric(4)
        refundRequset.setOut_refund_no("254635569865545626551423");
        refundRequset.setReason("test");
        refundRequset.setNotify_url("https://api.jitepay.com/v1/test");
        refundRequset.setFunds_account("");
        Amount amount = new Amount();
        amount.setRefund(BigDecimal.valueOf(0.1));
        amount.setCurrency("CNY");
        amount.setTotal(BigDecimal.valueOf(0.1));
        refundRequset.setAmount(amount);
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setMerchant_goods_id("");
        goodsDetail.setGoods_name("");
        goodsDetail.setUnit_price(BigDecimal.valueOf(0));
        goodsDetail.setRefund_amount(BigDecimal.valueOf(0));
        goodsDetail.setRefund_quantity(0);
        refundRequset.setGoods_detail(goodsDetail);
        System.out.println(Transaction.refund(refundRequset));
    }

    public static void queryRefundOrder() throws IOException {
        QueryRefundByIdRequst queryRefundByIdRequst = new QueryRefundByIdRequst();
        queryRefundByIdRequst.setMchid("2");
        System.out.println(Transaction.queryRefund(queryRefundByIdRequst));
    }

    public static void withdraw() {
        try {
            PayBankRequest payBankRequest = new PayBankRequest();
            payBankRequest.setMchid("2");
            payBankRequest.setAppid("2");
            payBankRequest.setOut_trade_no("1554616546552151546551");
            payBankRequest.setDescription("test");
            payBankRequest.setBank_no(RSAUtil.encrypt("6236691370002321599", Transaction.publicKey));
            payBankRequest.setTrue_name(RSAUtil.encrypt("彭玲艳",Transaction.publicKey));
            payBankRequest.setBank_code("CCB");
            payBankRequest.setBank_name("中国建设银行");
            payBankRequest.setBank_province("江苏省");
            payBankRequest.setBank_city("南京市");
            payBankRequest.setBank_branch_name("南京天元东路支行");
            payBankRequest.setAmount("0.01");
            payBankRequest.setNotify_url("https://api.jitepay.com/v1/test");
            System.out.println(Transaction.payBank(payBankRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryBank() throws IOException {
        QueryBankByIdRequest queryBankByIdRequest = new QueryBankByIdRequest();
        queryBankByIdRequest.setMchid("2");
        System.out.println(Transaction.queryPayBank(queryBankByIdRequest));
    }

    public static void verifyNotify() {

    }

}
