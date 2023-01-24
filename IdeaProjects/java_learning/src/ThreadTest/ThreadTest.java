package ThreadTest;

import PublicTest.PrivateTest.ProtectedTest.A;

import java.util.TreeMap;

/*
* There are many ways to implement multithreading
* 1.Firstly, Write a class extends class java.lang.Thread, override method "run()".
* Secondly, new a custom class object and call the method "start()".
* 2.Firstly, Write a class implements interface java.lang.Runnable, implement method "run()"
* */
public class ThreadTest {
    public static void main(String[] args) {
//        new MyThread().start();
        Thread t =  new MyThread();
        Thread t1 =  new MyThread();
        t.setName("Thread-0"); // Set the name of thread
        t1.setName("Thread-1"); // Set the name of thread
        t.start();
        t1.start();

    }

}

class MyThread extends Thread {
    public boolean run = true;//Be used to stop the thread
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (run) {
                Thread currentThread = Thread.currentThread();//currentThread will return the running Thread
                System.out.println(currentThread.getName()+ " i --> " + i);
            }else {
                //Save your data here

                //Stop the thread
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("MyRunnable i --> " + i);
        }
    }
}

class SleepTest {
    public static void main(String[] args) {
        /*
        * sleep is a static method, so when we call the method, it will make the current thread sleep
        * */

        Thread t = new MyThread();
        try {
            Thread.sleep(1000 * 5); //"sleep" method will make current thread sleep
            /*
            * Stop sleeping method "interrupt()"
            * Stopping sleep in this way depends on exception handling mechanism,
            * so we only can use try...catch to handle the exception
            * */
            t.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class StopThread {
    /*
    * How to correctly stop a thread
    * 1.Define a property name "run" in the thread class
    * 2.If the run = false, stop the thread
    * */
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t.run = false;//Stop thread
    }
}

class Account {
    private int balance;
//    Object obj = new Object();
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public Account(int balance) {
        this.balance = balance;
    }

    /*
    * Another way to implement synchronization
    * For example
    * But run in this way, the code is inefficient,
    * if the shared object is "this", use this method can make our code succinct
    *
    * Supplement:
    * About using StringBuffer(synchronized) and StringBuilder
    * 1.When we use local variable, we would better use StringBuilder, because it is more efficient
    * About collections:
    * Synchronous:
    * Vector, Hashtable
    * Asynchronous:
    * HashMap, HashSet, ArrayList
    * */
//  public synchronize void withdraw(int take)
    public void withdraw(int take) {
        /*
        * 1.If you want to make thread(1, 2, 3) queued for execution,
        * use the grammar synchronize(){} and fill in brackets with sharing object(Not necessarily "this" but commonly "this")
        * 2.If you want to make every thread synchronous, fill in brackets with any String
        * 3.The smaller the code block, the higher the efficiency
        * */
//        synchronized ("abc")
        synchronized (this){
            int before = getBalance();
            int after = before - take;
            try {
                Thread.sleep(1000);//Simulate network delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setBalance(after);
        }
    }
}


class ThreadSafetyIssue extends Thread{

    private Account a;

    public ThreadSafetyIssue(Account a) {
        this.a = a;
    }

    @Override
    public void run() {
        int take = 5000;
        a.withdraw(take);
        System.out.println(a.getBalance());
    }

    public static void main(String[] args) {
        /*
        *
        * 1. When a program runs in multithreading and the thread modifies data or access data,
        * which will cause data safety problem
        * The reason for the problem:
        *   1.Multithreaded concurrency
        *   2.Data sharing
        *   3.Data modification behavior
        * 2.In order to solve this problem,
        * Java introduces thread queued execution mechanism also called "Thread synchronization mechanism"
        * 3.Local variable never has the safety problem, because the variable without sharing
        *
        * Three ways to implement synchronization
        * 1.Add synchronize code block:
        *   synchronize(){}
        *   The threads sharing the object in the bracket will be queued for execution
        *
        * 2.Add "synchronize" in instance method:
        *   public synchronized void doSome(){}
        *   Equal to write the entire code block into synchronized(this){}
        *
        * 3.Add "synchronized" in static method:
        *   public static synchronized void doSome(){}
        *   All threads using this class will be queued for execution, even if the class objects are different
        *
        * Supplement:
        *   1.Local variable is stored in stack, every thread has its own stack, so the Local will not be shared
        *   2.Instance variable is stored in heap memory
        *   3.Static variable is stored in method area memory
        *   4.Heap memory and method area memory both are one piece, so they will be shared
        * */
        Account a = new Account(10000);
        ThreadSafetyIssue ts1 = new ThreadSafetyIssue(a);
        ThreadSafetyIssue ts2 = new ThreadSafetyIssue(a);
        ts1.start();
        ts2.start();
        /*
        * 5000
        * 5000
        * Data abnormal
        * */
//        System.out.println(a.getBalance());
    }
}