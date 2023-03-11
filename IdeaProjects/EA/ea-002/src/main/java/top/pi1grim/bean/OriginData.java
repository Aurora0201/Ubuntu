package top.pi1grim.bean;

import org.springframework.stereotype.Component;

@Component
public class OriginData {
    private String nickname;
    private String content;

    @Override
    public String toString() {
        return "OriginData{" +
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

    public OriginData() {
    }

    public OriginData(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }
}
