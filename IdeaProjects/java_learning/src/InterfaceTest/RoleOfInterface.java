package InterfaceTest;

public class RoleOfInterface {
    public static void main(String[] args) {
        Customer c = new Customer(new ChinaCook());
        c.order();
    }
}
interface Menu{
    void fanQieChaoDan();
    void yuXiangRouSi();
    void maPoTofu();
}

class ChinaCook implements Menu{
    @Override
    public void fanQieChaoDan() {
        System.out.println("c1");
    }

    @Override
    public void yuXiangRouSi() {
        System.out.println("c2");

    }

    @Override
    public void maPoTofu() {
        System.out.println("c3");

    }
}

class AmericanCook implements Menu{
    @Override
    public void fanQieChaoDan() {

    }

    @Override
    public void yuXiangRouSi() {

    }

    @Override
    public void maPoTofu() {

    }
}

class Customer{
    private Menu m;

    public Customer(Menu m) {
        this.m = m;
    }

    public Menu getM() {
        return m;
    }

    public void setM(Menu m) {
        this.m = m;
    }

    public void order(){
        m.fanQieChaoDan();
        m.maPoTofu();
        m.yuXiangRouSi();
    }
}