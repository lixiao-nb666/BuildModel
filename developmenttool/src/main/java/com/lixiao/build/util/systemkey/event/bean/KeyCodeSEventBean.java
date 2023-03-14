package com.lixiao.build.util.systemkey.event.bean;

import java.io.Serializable;

public class KeyCodeSEventBean implements Serializable {
    private String keyCodesStr;
    private int eventType;

    public KeyCodeSEventBean(String keyCodesStr, int eventType) {
        this.keyCodesStr = keyCodesStr;
        this.eventType = eventType;
    }

    public String getKeyCodesStr() {
        return keyCodesStr;
    }

    public void setKeyCodesStr(String keyCodesStr) {
        this.keyCodesStr = keyCodesStr;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "KeyCodeSEventBean{" +
                "keyCodesStr='" + keyCodesStr + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}
