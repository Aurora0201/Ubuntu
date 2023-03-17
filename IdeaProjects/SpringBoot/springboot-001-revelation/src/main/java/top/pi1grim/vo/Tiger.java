package top.pi1grim.vo;

import org.springframework.stereotype.Component;

@Component
public class Tiger {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Tiger() {

    }

    public Tiger(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
