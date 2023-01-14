package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;

/** Native下单 **/
public class NativeResponse {
    @JSONField(name = "code_url")
    private String codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
