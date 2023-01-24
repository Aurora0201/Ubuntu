package EnumTest;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(div(1,3));
        System.out.println(div(1,0));
    }
    public static Result div(int a, int b){
        //异常处理机制
        try {
            int c = a / b;
            return Result.SUCCESS;
        }catch (Exception e){
            return Result.FAIL;
        }
    }
}
/*
* If a method return countable results,
* we should better write an enum class as the return values;
* */
enum Result{
    SUCCESS,FAIL
}