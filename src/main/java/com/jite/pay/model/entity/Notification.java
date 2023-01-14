package com.jite.pay.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

/** 支付回调通知 */
public class Notification {

    private String id;

    @JSONField(name = "create_time")
    private String createTime;

    @JSONField(name = "event_type")
    private String eventType;

    @JSONField(name = "resource_type")
    private String resourceType;

    private String summary;

    private Resource resource;

    private String plaintext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
}
