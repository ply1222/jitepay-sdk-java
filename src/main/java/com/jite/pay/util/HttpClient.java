package com.jite.pay.util;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {

    private Map<String, String> header;

    public void setHeader(String key, String val) {
        if (header == null) {
            this.header = new HashMap<>();
        }
        this.header.put(key, val);
    }

    /**
     * GET请求
     *
     * @param url 请求地址
     * @return String
     */
    public String get(String url) {
        return get(url, null);
    }

    /**
     * GET请求
     *
     * @param url 请求地址
     * @param params 请求参数
     * @return String
     */
    public String get(String url, Map<String, String> params) {
        String result = null;
        HttpGet httpGet = new HttpGet(url);

        // 设置请求头
        if (header != null) {
            header.forEach(httpGet::setHeader);
        }

        // 表单参数
        if (params != null) {
            List<NameValuePair> pairs = new ArrayList<>();
            // 请求参数
            params.forEach((key, value)-> pairs.add(new BasicNameValuePair(key, value)));

            // 增加到请求 URL 中
            try {
                URI uri = new URIBuilder(new URI(url)).addParameters(pairs).build();
                httpGet.setUri(uri);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpGet);
            // 获取状态码
            //System.out.println(response.getVersion()); // HTTP/1.1
            //System.out.println(response.getCode()); // 200
            //System.out.println(response.getReasonPhrase()); // OK
            HttpEntity entity = response.getEntity();
            // 获取响应信息
            result = EntityUtils.toString(entity);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        for (String key : params.keySet()) {
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.substring(0, url.length() - 1);
    }

    /**
     * POST请求
     *
     * @param url 请求地址
     * @param body 请求主体
     * @return String
     */
    public String post(String url, Map<String, String> body) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            // 表单参数
            List<NameValuePair> pairs = new ArrayList<>();
            body.forEach((key, value)-> pairs.add(new BasicNameValuePair(key, value)));
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            // 获取响应信息
            result = EntityUtils.toString(entity);
            // 确保流被完全消费
            EntityUtils.consume(entity);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url 请求地址
     * @param body 请求主体
     * @return String
     */
    public String post(String url, String body) {
        String result = null;
        try {
            // 实例化
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            if (header != null) {
                header.forEach(httpPost::setHeader);
            }
            // 设置请求主体
            httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(httpPost);
            // 获取响应信息
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
