package Collections;

/**
 * 栈类，实现了一个存储int型的栈
 * @author binjunkai
 * @version 1.0
 *
 */
public class MyStack {
    private int[] a;
    private int idx;
    private int size;
    public MyStack() {
        this(10);
    }

    /**
     * 构造一个大小为{@link MyStack#size}的栈
     * @param size 初始化的大小
     */
    public MyStack(int size) {
        a = new int[size];
        this.size = size;
    }

    /**
     * 将元素sum插入到栈顶
     * @param sum 插入的元素
     */
    public void insert(int sum){
        if(idx < size){
            a[idx++] = sum;
            System.out.println("插入元素成功");
        }else System.out.println("容量已满插入失败");
    }

    /**
     * 弹出栈顶元素
     */
    public void pop(){
        if(!empty()) {
            System.out.println("弹出栈顶元素");
            idx--;
        } else System.out.println("栈空，弹出失败");
    }

    /**
     *
     * @param size 重置栈的大小
     */
    public void reSize(int size){
        if(size <= this.size){
            System.out.println("重置失败，容量需大于" + this.size);
        }else{
            System.out.println("容量重置为" + size);
            System.arraycopy(a,0,new int[size],0,a.length);
            this.size = size;
        }
    }

    /**
     * 返回栈顶元素
     * @return int
     *
     */
    public int top(){
        if(!empty())return a[idx-1];
        else{
            System.out.println("栈中没有元素");
            return -1;
        }
    }

    /**
     * 判断当前栈是否为空
     * @return boolean
     */
    public boolean empty(){
        return idx == 0;
    }

}

class Test{
    public static void main(String[] args) {
        MyStack stack = new MyStack(10);
        stack.insert(123);
        stack.insert(124);
        stack.insert(125);
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.top());
    }
}
