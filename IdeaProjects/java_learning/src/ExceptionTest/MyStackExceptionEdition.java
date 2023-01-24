package ExceptionTest;

/**
 * 栈类，实现了一个数组模拟的栈
 * @author binjunkai
 * @version 1.1
 *
 */
public class MyStackExceptionEdition {
    private Object[] a;
    private int idx;
    private int size;
    public MyStackExceptionEdition() {
        this(10);
    }

    /**
     * 构造一个大小为{@link MyStackExceptionEdition#size}的栈
     * @param size 初始化的大小
     */
    public MyStackExceptionEdition(int size) {
        a = new Object[size];
        this.size = size;
    }

    /**
     * 将元素sum插入到栈顶
     * @param sum 插入的元素
     */
    public void push(Object sum) throws MyException {
        if(idx < size){
            a[idx++] = sum;
            System.out.println("插入元素成功");
        }else throw new MyException("容量已满插入失败");
    }

    /**
     * 弹出栈顶元素
     */
    public void pop() throws MyException {
        if(!empty()) {
            System.out.println("弹出栈顶元素");
            idx--;
        } else throw new MyException("栈空，弹出失败");
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
            System.arraycopy(a,0,new Object[size],0,a.length);
            this.size = size;
        }
    }

    /**
     * 返回栈顶元素
     * @return int
     *
     */
    public Object top() throws MyException {
        if(!empty())return a[idx-1];
        else throw new MyException("栈中没有元素");
    }

    /**
     * 判断当前栈是否为空
     * @return boolean
     */
    public boolean empty(){
        return idx == 0;
    }
}

class Test {
    public static void main(String[] args){
        MyStackExceptionEdition st = new MyStackExceptionEdition();
//        st.insert( 1);
        try {
            st.top();
            System.out.println(st.top());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}