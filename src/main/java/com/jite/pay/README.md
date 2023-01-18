# 吉特支付 Java SDK
吉特支付 官方 Java 语言客户端开发库。

开发库由 core 和 service 组成：
- core 为基础库，包含自动签名和验签的 HTTP 客户端、回调处理、加解密库。
- service 为业务服务，包含业务接口和使用示例。

## 快速开始
### 安装

### 调用业务请求接口
以 JSAPI 下单为例，先构建 config 和 service，再发送请求
```java
/** JSAPI 下单为例 */
public class QuickStart {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        JsapiService service = new JsapiService.Builder().config(config).build(); // channel: alipay_pub alipay_lite wx_pub wx_lite
        //AppService service = new AppService.Builder().config(config).build(); // channel: alipay wx
        //H5Service service = new H5Service.Builder().config(config).build(); // channel: alipay_wap wx_wap
        //NativeService service = new NativeService.Builder().config(config).build(); // channel: alipay_qr wx_qr

        PrepayRequest request = new PrepayRequest();
        request.setChannel("wx_pub");
        request.setMchid("2");
        request.setAppid("3");
        request.setOutTradeNo(RandomUtil.numeric(18));
        request.setDescription("test");
        request.setNotifyUrl("https://api.jitepay.com/v1/test");

        Amount amount = new Amount();
        amount.setTotal(new BigDecimal("0.1"));
        amount.setCurrency("CNY");
        request.setAmount(amount);
        request.setAttach("");

        // 优惠功能
        Detail detail = new Detail();
        detail.setCostPrice(new BigDecimal("0.1"));
        request.setDetail(detail);

        // 支付者
        //Payer payer = new Payer();
        //payer.setOpenid("2088722402304925");//2088022632420470 2088722402304925
        //request.setPayer(payer);

        // 场景
        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setPayerClientIp("183.165.107.131");
        request.setSceneInfo(sceneInfo);

        System.out.println(JSONObject.toJSONString(service.prepay(request)));
    }
}
```

### 查询支付订单
```java
public class QueryOrder {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        JsapiService service = new JsapiService.Builder().config(config).build();
        
        QueryOrderRequest query = new QueryOrderRequest();
        query.setOutTradeNo("111111111111111111111");
        System.out.println(JSONObject.toJSONString(service.queryOrder(query)));
    }
}
```

### 关闭订单
```java
public class CloseOrder {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        JsapiService service = new JsapiService.Builder().config(config).build();

        CloseOrderRequest closeRequest = new CloseOrderRequest();
        closeRequest.setOutTradeNo("out_trade_no_001");
        service.closeOrder(closeRequest);
    }
}
```

### 退款
```java
public class Refund {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();
        
        RefundService service = new RefundService.Builder().config(config).build();

        RefundOrderRequest refund = new RefundOrderRequest();
        refund.setOutTradeNo("202212301450146570");
        refund.setOutRefundNo("202212301450146570");
        refund.setReason("退款");

        RefundAmount amount = new RefundAmount();
        amount.setRefund(BigDecimal.valueOf(1));
        amount.setTotal(BigDecimal.valueOf(1));
        amount.setCurrency("CNY");
        refund.setAmount(amount);
        System.out.println(JSONObject.toJSONString(service.create(refund)));
    }
}
```

### 查询退款
```java
public class QueryRefund {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        RefundService service = new RefundService.Builder().config(config).build();

        QueryRefundOrderRequest query = new QueryRefundOrderRequest();
        query.setOutRefundNo("202212131731323608");
        System.out.println(JSONObject.toJSONString(service.query(query)));
    }
}
```

### 转账
```java
public class Transfer {
    /** 商户号 */
    public static String merchantId = "";
    /** 商户API私钥 */
    public static String privateKey = "";
    /** 商户API公钥 */
    public static String publicKey = "";
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
        request.setMchid("2");
        request.setAppid("2");
        request.setOutTradeNo(RandomUtil.numeric(18));
        request.setRemark("test");
        request.setAmount(new BigDecimal("0.1"));
        request.setNotifyUrl("https://api.jitepay.com/v1/test");

        Payee payee = new Payee();
        payee.setIdentity("623052067001019****");
        payee.setIdentityType("BANKCARD_ACCOUNT");
        payee.setName("王*");
        payee.setAccountType(2);
        payee.setBankName("中国农业银行");
        payee.setBankBranchName("农行蚌埠淮河支行");
        payee.setBankProvince("安徽省");
        payee.setBankCity("蚌埠市");
        request.setPayee(payee);
        
        System.out.println(JSONObject.toJSONString(service.create(request)));
    }
}
```

### 查询转账
```java
public class QueryTransfer {
    /** 商户号 */
    public static String merchantId = "";
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
                .privateKey(privateKey)
                .apiKey(apikey)
                .build();

        TransferService service = new TransferService.Builder().config(config).build();

        QueryTransferOrderRequest request = new QueryTransferOrderRequest();
        request.setOutTradeNo("202301121755360812");
        System.out.println(JSONObject.toJSONString(service.query(request)));
    }
}
```

### 回调通知验签和解密
```java
public class Notification {
    @PostMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        try {
            //通知报文
            Config config = new Config.Builder().publicKey("").apiKey("").build();
            NotificationService service = new NotificationService(config);
            var body = service.parse(request);
            if (body != null || !body.isEmpty()) {
                //通知应答
                logger.info(body.toJSONString());

            } else {
                String result = "{\"code\":\"FAIL\",\"message\":\"失败\"}";
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(result);
                response.getWriter().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```