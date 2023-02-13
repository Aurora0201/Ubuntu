package com.javaweb.bank.bean;

/**
 * Account instance class
 * Provide a class to store the data
 * Usually one table corresponds to one class
 * @author binjunkai
 * @since 2.0
 * @version 2.0
 */
public class Account { //pojo,bean,domain...
    /**
     * Usually, the class to store the data will capsule corresponding fields that defined by wrapper class
     */
    //primary key
    private Long id;

    public Account() {
    }

    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    private String actno;
    private Double balance;
}
