package ThreadTest;

public class DeadLockTest {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        MyThread1 t1 = new MyThread1(o1, o2);
        MyThread2 t2 = new MyThread2(o1, o2);
        t1.start();
        t2.start();
        //Never stop
        /*
        * 1.The instance tells us, we should not use "synchronize" nested,
        * because it may lead to deadlock
        * 2."synchronized will make program inefficient"
        *
        * How to deal with the thread safety problem?
        * 1.As far as possible use local variable instead of static variable or instance variable
        * 2.If it is necessary for using instance variable, then use multiple objects
        * 3.If we can't use 1 or 2, then use "synchronized"
        * */
    }
}

class MyThread1 extends Thread {
    private Object o1;
    private Object o2;

    public MyThread1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        synchronized (o1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (o2) {
            }
        }
    }
}
class MyThread2 extends Thread {
    private Object o1;
    private Object o2;

    public MyThread2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        synchronized (o2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (o1) {

            }
        }
    }
}