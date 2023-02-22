package com.emotion.analysis.bean;

import java.util.Date;

/**
 * Content bean
 * @author binjunkai
 * @since 1.0
 * @version 1.0
 */
public class Content {
    String username;
    Date time;
    String content;
    Double probability;
    String status;

    @Override
    public String toString() {
        return "Content{" +
                "username='" + username + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", probability=" + probability +
                ", status='" + status + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public Date getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public Double getProbability() {
        return probability;
    }

    public String getStatus() {
        return status;
    }

    public Content(String username, Date time, String content, Double probability, String status) {
        this.username = username;
        this.time = time;
        this.content = content;
        this.probability = probability;
        this.status = status;
    }

    public Content() {
    }
}
