package AbstractTest;

public class Test {
    public static void main(String[] args) {
        Card c = new CreditCard(1000, 12345, 100, 90);
        c.consume(100);
        c.consume(100);

        Card c1 = new DebitCard(1000, 12345, 100);
        c1.consume(100);
        c1.consume(100);

    }
}

abstract class Account{
    private int actno;
    private int password;
    private double balance;
    public Account() {
    }

    public Account(int actno, int password) {
        this.actno = actno;
        this.password = password;
    }

    public Account(int actno, int password, double balance) {
        this.actno = actno;
        this.password = password;
        this.balance = balance;
    }

    public int getActno() {
        return actno;
    }

    public int getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setActno(int actno) {
        this.actno = actno;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void consume(double balance);

}

class CreditCard extends Card{
    private double credit;

    public CreditCard(int actno, int password, double balance, double credit) {
        super(actno, password, balance);
        this.credit = credit;
    }

    @Override
    public void consume(double balance) {
        if(getBalance() >= balance){
            setBalance(getBalance() - balance);
            System.out.println("信用卡支付成功，余额：" + getBalance());

        } else {
            setBalance(getBalance() - balance);
            System.out.println("信用卡余额不足，将使用信用支付，余额：" + getBalance());
        }
    }

}
class DebitCard extends Card{
    public DebitCard() {
    }

    public DebitCard(int actno, int password) {
        super(actno, password);
    }

    public DebitCard(int actno, int password, double balance) {
        super(actno, password, balance);
    }

    @Override
    public void consume(double balance) {
        if(getBalance() >= balance){
            setBalance(getBalance() - balance);
            System.out.println("储蓄卡支付成功，余额：" + getBalance());

        } else {
            System.out.println("储蓄卡余额不足，支付失败，余额：" + getBalance());
        }
    }
}

class Card extends Account{
    public Card() {
    }
    public Card(int actno, int password) {
        super(actno, password);
    }
    public Card(int actno, int password, double balance) {
        super(actno, password, balance);
    }

    public void consume(double balance) {}
}