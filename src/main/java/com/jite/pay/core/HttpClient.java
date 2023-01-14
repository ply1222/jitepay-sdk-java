package com.jite.pay.core;

import com.jite.pay.exception.HttpException;
import com.jite.pay.exception.ServiceException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class HttpClient {
    private final Signature signature;

    public HttpClient(Config config) {
        RSAUtil signer = new RSAUtil(requireNonNull(config.getSerialNumber()), config.getPublicKey(), requireNonNull(config.getPrivateKey()));
        this.signature = new Signature(requireNonNull(config.getMerchantId()), signer);
    }

    /**
     * GET请求
     *
     * @param url 请求地址
     * @return String
     */
    public String httpGet(String url) {
        String result;
        try {
            HttpGet httpGet = new HttpGet(url);

            // 设置请求头
            httpGet.setHeader("Accept", ContentType.APPLICATION_JSON);
            httpGet.setHeader("Content-Type", ContentType.APPLICATION_JSON);
            httpGet.setHeader("Authorization", signature.getAuthorization(url, "GET", ""));

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 获取响应信息
            result = EntityUtils.toString(entity);
            if (response.getCode() != 200) {
                throw new ServiceException(response.getCode(), result);
            }
        } catch (IOException | ParseException e) {
            throw new HttpException(e.getMessage());
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url  请求地址
     * @param body 请求主体
     * @return String
     */
    public String httpPost(String url, String body) {
        String result;
        try {
            // 实例化
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            httpPost.setHeader("Accept", ContentType.APPLICATION_JSON);
            httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON);
            httpPost.setHeader("Authorization", signature.getAuthorization(url, "POST", body));
            // 设置请求主体
            httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);
            // 获取响应信息
            result = EntityUtils.toString(response.getEntity());
            if (response.getCode() != 200) {
                throw new ServiceException(response.getCode(), result);
            }
        } catch (IOException | ParseException e) {
            throw new HttpException(e.getMessage());
        }
        return result;
    }
}
