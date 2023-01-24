package ExceptionTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {
/*
     * Exception 类的子类是编译时异常，必须在编写代码时解决
     * 第一种处理方式，异常上抛相当于推卸责任，将异常抛给上级处理
     * 一般不建议在main中使用throws，而是使用try catch
    public static void main(String[] args) throws ClassNotFoundException{
        doSome();
    }
*/
    /*
    * 第二种处理方式，使用try catch进行异常捕捉，就不会再上抛，相当于异常解决了
    */
    public static void main(String[] args){
        try {
            doSome();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void doSome() throws ClassNotFoundException{
        System.out.println("doSome");
    }

}

class ExceptionTest02{
    /*
    * 抛出异常只能抛出相等或者父类（throw larger range exception）
    * */
    public static void main(String[] args) {
        m1();
    }
    public static void m1(){
        System.out.println("m1 start");
        try {
            m2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("m1 end");
    }
    public static void m2() throws Exception {
        System.out.println("m2 start");
        FileInputStream fileInputStream = new FileInputStream("/usr/test.txt");
        System.out.println(fileInputStream.read());
        System.out.println("m2 end");
    }
}

class ExceptionTest01{
    public static void main(String[] args) {
        /*
        * try可以接多个catch
        * 建议每种情况都精确的处理
        * 捕捉到异常后，会从上到下依次匹配
        * 所以catch中的异常要从上到下，从小到大写
        *
        * 那么对于抛出和捕捉该怎么选择呢
        *   如果希望调用者解决则选择throws异常
        *   其他情况使用捕捉
        * */
        try {
            System.out.println(1/0);
        } catch (ArithmeticException e) {
//            System.out.println(e);
//            System.out.println("出现算术异常");
            /*
            * 在实际工作中，捕捉到异常后，一般是要打印堆栈信息
            * 要养成这种好习惯
            * */
            e.printStackTrace();
        }
        System.out.println("main done");
    }
}

class ExceptionTest03 {
    /*
    * 关于try catch中的finally字句
    * 即使try中的代码块出现了异常，finally字句也会运行，并且是最后运行的
    * finally必须与try一起出现
    *
    * finally中的语句块一般用来释放资源，即使前面出现错误，语句块也能执行
    * */
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/usr/test.txt");
            String txt = null;
            txt.toString();
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}

class ExceptionTest04 {
    public static void main(String[] args) {
        try {
            System.out.println("main");
            return;
        }finally {
            /*
            * 即使try中return了（方法结束了），finally中的语句块也会执行
            * 但是如果JVM退出了（System.exit(0)），finally就不会执行了
            *
            * */
            System.out.println("finally done");
        }
    }
}


class ExceptionTest05 {
    /*
    * java中两条亘古不变的语法
    * 1.方法体中的代码必须自上而下的依次执行
    * 2.return语句一旦执行，整个方法必须结束，return语句一定是最后执行的
    * */
    public static void main(String[] args) {
        System.out.println(m3());
    }
    /*
    * 根据上面的语法规则，按理来说，这个函数的返回值是101，因为先运行finally中的i++语句才返回i
    * 但是，这样就违背了代码自上而下的运行的规则，所以这是一个特殊的例子，
    * 首先程序用了一个临时变量 j 来存储 i，然后运行 i++，最后返回 j，所以这个程序的返回值是100
    *
    * 所以其实这个程序的执行顺序是，finally在return执行之后，return返回之前执行的
    * 因为return执行时返回的i = 100，但此时函数还没有返回，还需要继续执行finally语句块，
    * 所以此时使用了一个临时变量 j 来存储要返回的值，然后对 i 进行自增操作，最后返回 j
    * 这是SUN公司的一个折中的办法
    * */
    public static int m1() {//返回100
        int i = 100;
        try {
            return i;
        }finally {
            i++;
        }
    }

    public static int m2() {//返回101
        int i = 100;
        try {
            return i;
        }finally {
            i++;
            return i;
        }
    }

    public static int m3() {
        int i = 1;
        i = ++i;
        return i;
    }
}