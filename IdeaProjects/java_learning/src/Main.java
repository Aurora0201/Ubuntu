import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        List<ReturnVO> list = new ArrayList<>();
        ReturnVO returnVO = list.get(0);

    }

    public static void type(Status status) {
        Arrays.stream(Status.values())
                .filter(s -> s.equals(status))
                .forEach(System.out::println);
    }


}

enum Status{
    OK(10, "连接成功"), NO(15, "连接失败");


    private final int code;
    private final String msg;

    Status(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

//    public static int getCodeByMsg(Status status){
//        return Arrays.stream(values())
//                .filter(t -> t.getMsg().equals(status))
//                .map(Status::getMsg)
//                .findFirst()
//                .get()
//    }
}

class ReturnVO{
    private int code;
    private String msg;

    public static ReturnVO getByStatus(Status status) {
        
        return null;
    }
}