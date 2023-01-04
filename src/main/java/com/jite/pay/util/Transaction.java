package com.jite.pay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jite.pay.model.request.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Transaction {

    public static String schema = "PAY-SHA256-RSA2048";

    public static String host = "https://api.jitepay.com";

    /**
     * 商户号
     */
    public static String mchid = "2";

    /**
     * RSA公钥
     */
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq3hpNTHzYCXf5FdPdMiQGNyOmO19QVZlMS09U5ZnuegJEdSTqwryJKeZTV+u6YMrn9i0IBZlpCIvIGpRL9mhGORt5Abr2fhUktVjsXJVF3jWsdw3EbSf127+2wrvrjgsrj3Qwo6pOj7YIOLof6oyX3CjtpzN6LxwUfGsxk5/rnr0rFUMQ7xgkXfsUEfqeYN4D386Z3cxKw4NsnZ4KMMcIlbgKL1miwKNsWQjGqN/cSGD+H3NdKv7RENKx7ZaVvQTyjPU+vCqmFSpw/kJP9tF5rfIC4FMXTXSdtMNB282CZ7JxJgqb+2ppl5OtIIH3KCEuqAxR9nFFwGVLhfEc0nGeQIDAQAB";

    /**
     * RSA私钥
     */
    public static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCreGk1MfNgJd/kV090yJAY3I6Y7X1BVmUxLT1Tlme56AkR1JOrCvIkp5lNX67pgyuf2LQgFmWkIi8galEv2aEY5G3kBuvZ+FSS1WOxclUXeNax3DcRtJ/Xbv7bCu+uOCyuPdDCjqk6Ptgg4uh/qjJfcKO2nM3ovHBR8azGTn+uevSsVQxDvGCRd+xQR+p5g3gPfzpndzErDg2ydngowxwiVuAovWaLAo2xZCMao39xIYP4fc10q/tEQ0rHtlpW9BPKM9T68KqYVKnD+Qk/20Xmt8gLgUxdNdJ20w0HbzYJnsnEmCpv7ammXk60ggfcoIS6oDFH2cUXAZUuF8RzScZ5AgMBAAECggEALDDaHGWFLZBVRUnjJlvSFzYwYeVC1KXpamUYWwR2MwlD3R6F+BzYDu5KqhAwyemOQqHcujBLfaN5tcbwqX5S8FFeqNfHzOMdGMJ58O9gUq5H1orEfoGoeCMY92a4IpRDn5w6wwl1P5eWp9MSzGQWm1YyOwvqXULDR7sbJfhxG4wT7n/ctzYs1vMrqCSbqyRP//q3oGKNOq0CgUzS05EVrwxyz71mZLl3ISnaOq34cH+vU9N9Agze3AdrC/M+Z9X6g4FICEOtAmPE1JXoZk9yklQrqb6l+VKvikOgM0AFxF6A3ll3FRXWWcSP8O/FjFtnqkayKWwT0+O0VVui9b9KAQKBgQDrRLqpVcCg0SFLKn4R6VxfdCXz/7d3bQULgiBZ6C4rvpt6GBFsw2ke7sjRaJYjvkqecQtwYl65vsoNCH1hvbyC3gaC58JkLMIU2uM8b8sL3GEiz+2PvGuPggGWXrdyBLnLg2i1dSGqxA+2LKbpuCV7wzCffXl7eALJHI31YAO0YQKBgQC6lIA8zxSDvkbjLnRF4ODuxPVtZwTJRlKIK8ysawW/fczFRyR4lJVXro3t/EHgAt2whIY02iHVMlGTDz8ekLkuvneJKXdAW0CA9Wlj+/2KQQsqCesa/tBAJy6cWyv5Lv8LVlbfnYHjD9rU9CQi5lpqw4C4cFcsrAHLefhEfW3JGQKBgCGmvQRHjbvy7c4wj8PEG0BT/rG92+IrJ9OTk0kI2sHLC7YVBzkFYl3YTcUWLpOCPm4XQUmb6GytC319v2FhoDsfwtKqj7WAaWpOPL6CRwq1RPeTwikTDFeEgvGdLqQSZPjlHO8Hh/9C9/RYwq8fdc0UCDpn2h589fkKKov0ZdNBAoGAfyRAuq9WSGw6PAdk3lVekfaPVAzWex27keVe5MNNOG9OQcS3+p8toYFmYBz8+tyZGvdDyPI4CeLvKapDFd4DAvJx3HrwM1+7deVF+wc1f6fRJsV5e3zWhlDs90k9juFSlPQx4NGhOAyOz3zKvyl/xa8RoR2UmfFgi7rCzlE2pckCgYEAwhBs5IPRiB8MTDGptASi3BJfNSK5U0ustIHA68MnB9s1LXhLv5G6xhQi9dWpPmFGpD5AF3MgdMbAXwERzh4gVXkiMq38zkodyuFVOi2ixVtJZGEwxcy0t/YU5z8oAkAcjdCagVyTtUd5bm5fjpRnssWIXVFld0ZMaQ5vScPmqF4=";

    /**
     * 验证密钥
     */
    public static String verifyKey;

    /**
     * 证书序列号
     */
    public static String serialNo = "DYK60QJXXTDJASYJBTGZC7YTEPKTEBXTQCXCNW2O";

    public static void main(String[] args) {
        //加密
        String url = "http://localhost:8181/v1/pay/transactions/out-trade-no/202212061800123456";
        System.out.println(getToken("Get", url, null));



        //通知
        //String s = buildNotify("1554209980", "c5ac7061fccab6bf3e254dcf98995b8c", "123456");
        //System.out.println(RSA.sign(privateKey, s));
    }
    public static String jsapi(PrepayRequest trade) throws IOException {
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("out_trade_no", trade.getOut_trade_no());
        request.put("channel", trade.getChannel());
        request.put("mchid", trade.getMchid());
        request.put("appid", trade.getAppid());
        request.put("description", trade.getDescription());
        request.put("notify_url", trade.getNotify_url());
        request.put("amount", trade.getAmount());
        request.put("payer", trade.getPayer());
        request.put("scene_info", trade.getScene_info());
        request.put("settle_info", trade.getSettle_info());
        String url = host + "/v1/pay/transactions/jsapi";
        return httpPost(url, JSON.toJSONString(request));
    }

    public static String queryOrder(QueryOrderByIdRequest trade) {
        TreeMap<String, String> request = new TreeMap<>();
        request.put("mchid",trade.getMchid());
        String url = host + "/v1/pay/transactions/out-trade-no/254635569865545626551423";//商户订单号
        return httpGet(url,request);
    }

    public static String refund(RefundRequset refund) {
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("out_trade_no",refund.getOut_trade_no());
        request.put("out_refund_no",refund.getOut_refund_no());
        request.put("reason", refund.getReason());
        request.put("notify_url",refund.getNotify_url());
        request.put("funds_account",refund.getFunds_account());
        request.put("amount",refund.getAmount());
        request.put("goods_detail",refund.getGoods_detail());
        String url = host + "/v1/refund/domestic/refunds";
        return httpPost(url, JSON.toJSONString(request));
    }

    public static String queryRefund(QueryRefundByIdRequst refund) {
        TreeMap<String, String> request = new TreeMap<>();
        request.put("mchid",refund.getMchid());
        String url = host + "/v1/refund/domestic/refunds/2546355698655456121251223";//退款单号
        return httpGet(url,request);
    }

    public static String payBank(PayBankRequest payBank) {
        TreeMap<String, Object> request = new TreeMap<>();
        request.put("mchid",payBank.getMchid());
        request.put("appid",payBank.getAppid());
        request.put("out_trade_no",payBank.getOut_trade_no());
        request.put("description",payBank.getDescription());
        request.put("bank_no",payBank.getBank_no());
        request.put("true_name",payBank.getTrue_name());
        request.put("bank_code",payBank.getBank_code());
        request.put("bank_name",payBank.getBank_name());
        request.put("bank_province",payBank.getBank_province());
        request.put("bank_city",payBank.getBank_city());
        request.put("bank_branch_name",payBank.getBank_branch_name());
        request.put("amount",payBank.getAmount());
        request.put("notify_url",payBank.getNotify_url());
        String url = host + "/v1/pay/pay_bank";
        return httpPost(url, JSON.toJSONString(request));
    }

    public static String queryPayBank(QueryBankByIdRequest payBank) {
        TreeMap<String, String> request = new TreeMap<>();
        request.put("mchid",payBank.getMchid());
        String url = host + "/v1/pay/query_bank/1554616546552151546551";//商户订单号
        return httpGet(url,request);
    }

    /**
     * 验证签名
     *
     * @return JSONObject
     */
    public static JSONObject verify(HttpServletRequest request) throws Exception {
        //获取请求头
        String pay_nonce = request.getHeader("Pay-Nonce");
        String pay_signature = request.getHeader("Pay-Signature");
        String pay_timestamp = request.getHeader("Pay-Timestamp");
        String pay_serial = request.getHeader("Pay-Serial");

        //获取请求主体
        String responseBody = Transaction.getResponseBody(request);
        if (responseBody == null || responseBody.equals("")) {
            return null;
        }
        //验证签名
        if (Transaction.verifySignature(pay_timestamp, pay_nonce, responseBody, pay_signature)) {
            //解密数据
            JSONObject obj = JSONObject.parseObject(responseBody);
            JSONObject res = obj.getJSONObject("resource");
            byte[] ciphertext = res.getBytes("ciphertext");
            String associated_data = res.getString("associated_data");
            String nonce = res.getString("nonce");
            String result = AesUtil.decrypt(ciphertext, verifyKey, nonce, associated_data);
            obj.put("result", JSONObject.parse(result));
            return obj;
        }
        return null;
    }

    /**
     * 获取时间戳
     *
     * @return long
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取请求主体
     *
     * @return String
     */
    public static String getResponseBody(HttpServletRequest request) {
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                sb.append(inputStr);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签名生成
     *
     * @param method 请求方式
     * @param url    请求地址
     * @param body   请求主体 GET请求为空
     * @return String
     */
    public static String getToken(String method, String url, String body) {
        String nonceStr = AesUtil.nonce();
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = RSAUtil.sign(privateKey, message);
        return "mchid=\"" + mchid + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + serialNo + "\","
                + "signature=\"" + signature + "\"";
    }

    /**
     * 验证签名
     *
     * @param method 请求方式
     * @param url    请求地址
     * @param body   请求主体
     * @param map    请求Authorization头
     * @return Boolean
     */
    public static Boolean verifyToken(String method, String url, String body, Map<String, String> map) {
        String data = buildMessage(method, url, Long.parseLong(map.get("timestamp")), map.get("nonce_str"), body);
        return RSAUtil.verifySign(publicKey, data, map.get("signature"));
    }

    /**
     * 构造验签名串
     *
     * @param method    请求方式
     * @param url       请求地址
     * @param timestamp 时间戳
     * @param nonceStr  随机数
     * @param body      请求主体
     * @return String
     */
    private static String buildMessage(String method, String url, long timestamp, String nonceStr, String body) {
        try {
            URL url_parts = new URL(url);
            url = url_parts.getPath() + (url_parts.getQuery() != null ? "?" + url_parts.getQuery() : "");
            return method + "\n"
                    + url + "\n"
                    + timestamp + "\n"
                    + nonceStr + "\n"
                    + body + "\n";
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * 将字符串转换为Map
     *
     * @param str Map字符串
     * @return Map
     */
    public static Map<String, String> parseToken(String str) {
        str = str.substring(schema.length() + 1);
        String[] strArr = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String string : strArr) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            String key1 = key.trim();
            String value1 = value.trim().replace("\"", "");
            map.put(key1, value1);
        }
        return map;
    }

    /**
     * 签名生成
     *
     * @param timestamp    应答时间戳 Pay-Timestamp
     * @param nonce        应答随机串 Pay-Nonce
     * @param responseBody 应答主体
     * @return String
     */
    public static String getSignature(String timestamp, String nonce, String responseBody) {
        String data = buildNotify(timestamp, nonce, responseBody);
        return RSAUtil.sign(privateKey, data);
    }

    /**
     * 签名验证
     *
     * @param timestamp    应答时间戳 Pay-Timestamp
     * @param nonce        应答随机串 Pay-Nonce
     * @param responseBody 应答主体
     * @param signature    应答签名
     * @return
     */
    public static Boolean verifySignature(String timestamp, String nonce, String responseBody, String signature) {
        String data = buildNotify(timestamp, nonce, responseBody);
        return RSAUtil.sign(privateKey, data).equals(signature);
    }

    /**
     * 构造验签名串
     * header: Pay-Nonce,Pay-Signature,Pay-Timestamp,Pay-Serial
     *
     * @param timestamp    应答时间戳 Pay-Timestamp
     * @param nonce        应答随机串 Pay-Nonce
     * @param responseBody 应答主体
     * @return String
     */
    private static String buildNotify(String timestamp, String nonce, String responseBody) {
        return timestamp + "\n"
                + nonce + "\n"
                + responseBody + "\n";
    }

    /**
     * get请求
     *
     * @param url   请求地址
     * @return String
     */
    public static String httpGet(String url) {
        HttpClient client = new HttpClient();
        client.setHeader("Authorization", schema + " " + getToken("GET", url, ""));
        return client.get(url);
    }

    /**
     * get请求
     *
     * @param url   请求地址
     * @param query 请求参数 如: ?mchid=1
     * @return String
     */
    public static String httpGet(String url, Map<String, String> query) {
        HttpClient client = new HttpClient();
        url = url + client.buildRequestUrl(query);
        client.setHeader("Authorization", schema + " " + getToken("GET", url, ""));
        return client.get(url);
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param body 请求主体
     * @return String
     */
    public static String httpPost(String url, String body) {
        HttpClient client = new HttpClient();
        client.setHeader("Authorization", schema + " " + getToken("POST", url, body));
        return client.post(url, body);
    }
}
