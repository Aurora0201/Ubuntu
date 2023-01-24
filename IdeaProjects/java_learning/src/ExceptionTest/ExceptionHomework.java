package ExceptionTest;

import java.util.Scanner;

public class ExceptionHomework {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        System.out.print("请输入用户名：");

        Scanner pass = new Scanner(System.in);
        System.out.print("请输入密码：");
        UserService us = new UserService();
        try {
            us.register(user.next(), pass.next());
            System.out.println("用户注册成功!");
        }catch (UserException e) {
            e.printStackTrace();
        }
    }
}

class UserService {
    String userName;
    String password;

    public UserService() {
    }
    public void register(String userName, String password) throws UserException{
        //开发经验 可以把userName == null 写成 null == userName 防止手滑
        // "abc".equals(userName) 比 userName.equals("abc")好
        if(userName == null || password == null || userName.length() < 6 || userName.length() > 14) throw new UserException("用户名长度不符合规则");
        this.userName = userName;
        this.password = password;
        System.out.println("注册成功");
    }
}

class UserException extends Exception{
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}