package com.jite.pay;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.model.entity.Amount;
import com.jite.pay.model.entity.Bankcard;
import com.jite.pay.model.entity.Detail;
import com.jite.pay.model.entity.SceneInfo;
import com.jite.pay.model.request.PrepayRequest;
import com.jite.pay.model.request.QueryOrderRequest;
import com.jite.pay.model.request.QueryTransferOrderRequest;
import com.jite.pay.model.request.TransferRequest;
import com.jite.pay.service.*;

import java.math.BigDecimal;

public class JsPayController {

    /** 商户号 */
    public static String merchantId = "2";
    /** 商户API公钥 */
    public static String publicKey = "";
    /** 商户API私钥 */
    public static String privateKey = "";
    /** 商户证书序列号 */
    public static String merchantSerialNumber = "d14fea32f79b52f03d7a7624a711a6ec";
    /** 商户API密钥 */
    public static String apikey = "VMjuVYmRy7E3qlx8tSkjlSmxj3Dvm5U8";

    public static void main(String[] args) {
        Config config = new Config.Builder()
                .merchantId(merchantId)
                .serialNumber(merchantSerialNumber)
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        TransferService service = new TransferService.Builder().config(config).build();

        QueryTransferOrderRequest request = new QueryTransferOrderRequest();
        request.setOutTradeNo("");
        System.out.println(JSONObject.toJSONString(service.query(request)));
    }
}