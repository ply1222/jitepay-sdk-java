package com.jite.pay;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.model.entity.*;
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
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAouHLaHPuUb8Y7tc11A3oqeR5OkiRbwy2RnQhTR+HwOGWFB8afIxhZTQEnw+mPeAChrxy9YhJPxOoRiZaEecWIfKHkOYi+fX+p9/8j4DvGI+GjtRB3RlwKG+nQK0iBu3xWRsldCKsW5sDUaEzARn7MqXTC0d4sGc+rAe/FjsiVq1fJVHUme6Ay6fpKts76ZVzlE8UviZzCHNpvsj0jxqvCdfqWzfQj4APNZaTOuE7iEGL/z7SEjJP9ZdqFh95gngLJyBkgs8fnSsY/XKnx+VjbbDcTPP/4cU/pdFF0aqLuaMrL0IQpbcYy0Td6FzxSNzK2y1s3zYhJtTLweyUTE60UwIDAQAB";
    /** 商户API私钥 */
    public static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCi4ctoc+5Rvxju1zXUDeip5Hk6SJFvDLZGdCFNH4fA4ZYUHxp8jGFlNASfD6Y94AKGvHL1iEk/E6hGJloR5xYh8oeQ5iL59f6n3/yPgO8Yj4aO1EHdGXAob6dArSIG7fFZGyV0IqxbmwNRoTMBGfsypdMLR3iwZz6sB78WOyJWrV8lUdSZ7oDLp+kq2zvplXOUTxS+JnMIc2m+yPSPGq8J1+pbN9CPgA81lpM64TuIQYv/PtISMk/1l2oWH3mCeAsnIGSCzx+dKxj9cqfH5WNtsNxM8//hxT+l0UXRqou5oysvQhCltxjLRN3oXPFI3MrbLWzfNiEm1MvB7JRMTrRTAgMBAAECggEASV1osEifjKSFh3baIQSOyo9FZ1IuZ5WTOFKweTt9ewxg+/kyhez5JYtzlW2IFJCksqmJIjzbuRSSk95MbYnntyy1kTeHg40gwd6qtLx/dVGYaxcB/6OomB4KeKBDFlnwfpEyoofHmI9OxGLWRWW9doeocokjvFkUqonmsQ27nsxJ50GbT+LgT/WqB/r+T+IihLq6LdcjBlwQC/WfrNHldpMupPtvGmO0vcC09lNBGD5F+h7CisoJJgbU7kXu+cgbHGBD7Nq+rEvLgFHnjl9ipCSHgokEcKe82NaF4yc3+W1dA0v6XkjdLj8h7Znn2WDTo3zeITXGlfOxyugN0SE2qQKBgQDdMHsb9APRIH59PqJFD89jL8jL7hs7vEAZdk9cokW0+W172vmagW7pwmRfQvergb8Ja2Gug/F9oed7gyBgs5nby9UZIWTe3KtX6frVRoihvXb9Yi18rMJ9lY7LtP+3p0jj63L0DdNn7YAmFweA0pOybyj6x9y+kBnrx9nX6OMnhQKBgQC8hCmq7JPh89YoexCpQoys54Bacobb+1sSrKBhJ7rTvtZ2Knj1BbVdh8EijdI9KknyZ48U66z+I6R1b/70u8MPB81GAuOTMPVaexUHEPORtUxElTWLlCmfkYbnivqjo7dWfxxgeEJc/G5me1TI89sRmkwm+Gpkt0Qtof9xNu439wKBgGIUEUK/3MFqaywWDdYZwJf2pE7o8eJ3AuVHdMFaoxYwU7/LxUohgpDcxa0IANJn4dHHb7T2hKp0lDRMXJsEiIDRzVgrWpMHvmJpOfRAJm2xmYWZdxoFcOhG3N6vD4TcBJIr4Pke+FLpGR3KsGUK+rrwV3d8EAHf296U65+1gKQRAoGAMLIvFUDxXl+fRWusvRw8vHk8daC5519BgkxnTVF2+DWGrpWAE0L7O4LSx/s8gKJI4b4QfsX2NNu+IrvgbxWFaH+KbfhXEvGFn27F2sJtOIlNfzXP1BNcwSRVZcBHyDeFJ2nEScMm2WA3oG9hUltzjlN+Ml7fFM8mZGdBVdxrorcCgYA4ULjrjTawADV1oVDSxiLRDBcWVeQxdjWZNt1ZLLG4g11DMr0FKv6zEvFPbX1URwjBwFfHIXe/qJmYyUf+q6F8h4SUWuW2y5knqazjxCDlQzG2SsFF0cqpDlnQhfEV/IzwEL+sYPglfkGRBjwuU1h7mMi7nIan1xbCt+/75qN/6w==";
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
        request.setOutTradeNo("20230117164809");
        System.out.println(JSONObject.toJSONString(service.query(request)));
    }
}