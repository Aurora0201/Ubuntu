package top.pi1grim.model;

import java.net.Inet4Address;

public class User {
    private Integer id;
    private String actno;
    private Double balance;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public User() {
    }

    public User(Integer id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }
}
