package ExceptionTest;

public class ThrowsException {
    public static void main(String[] args) {

    }

    public void m1() throws Exception{

    }
    public void m2() throws NullPointerException{

    }

}
/*
* 子类在重写父类方法时，只能抛同等级或者范围更小的异常
* 如父类->Exception 子类->NullPointerException
* 但是在开发中，一般重写不会改变参数
* */
class Son extends ThrowsException{
    @Override
    //编译不报错
    public void m1() throws NullPointerException {}

/*
    @Override
    public void m2() throws Exception {}
    编译报错
*/
}
