package ThreadTest;

public class Daemon {
    public static void main(String[] args) {
        MyThread3 t = new MyThread3();
        t.setName("Daemon");
        t.setDaemon(true);//Set thread t to Daemon, so when the main thread stop, the daemon stop too.
        t.start();

        int i = 0;
        while (i < 10) {
            System.out.println("Main i -> " + i++);
        }
    }
}

class MyThread3 extends Thread {
    public void run() {
        while (true) {
            int i = 0;
            System.out.println(Thread.currentThread().getName() + " " + (++i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


