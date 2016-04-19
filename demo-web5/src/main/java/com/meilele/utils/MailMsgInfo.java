package com.meilele.utils;

import java.io.Serializable;

public class MailMsgInfo implements Serializable {

    private static final long serialVersionUID = 5747147191069917968L;

    private String            sendAddress;

    private String            subject;

    private String            content;

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
