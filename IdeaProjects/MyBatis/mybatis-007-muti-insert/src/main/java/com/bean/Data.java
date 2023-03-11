package com.bean;

public class Data {
    private String nickname;
    private String content;

    @Override
    public String toString() {
        return "Data{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Data() {
    }

    public Data(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }
}
