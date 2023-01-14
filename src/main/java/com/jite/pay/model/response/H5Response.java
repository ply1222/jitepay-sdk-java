package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class H5Response {
    @JSONField(name = "h5_url")
    private String h5Url;

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }
}
