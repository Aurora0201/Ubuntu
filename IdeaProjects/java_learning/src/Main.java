import java.util.Objects;

public class Main{
    public static void main(String[] args) throws Exception {
        Content content = Content.ABC;
        System.out.println(content.name());
//        Content.getContent();

    }

}

enum Content {
    ABC("123"), AB("12"), C("3");

    private final String content;

    Content(String content) {
        this.content = content;
    }

//    public static String getContent(Content content) {
//        Content[] values = values();
//        for (Content item : values) {
//            item.getContent();
//        }
//    }

    public static String getUrl(Content contentType) {
        if (Objects.isNull(contentType)) {
            return null;
        }
        Content[] values = values();
        for (Content tmp : values) {
            if (tmp.equals(contentType)) {
                return tmp.getUrl();
            }
        }
        return null;
    }
}