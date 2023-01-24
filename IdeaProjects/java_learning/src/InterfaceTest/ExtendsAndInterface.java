package InterfaceTest;

public class ExtendsAndInterface {
    public static void main(String[] args) {
        Flyable f = new Cat();
        f.fly();

        Animal a = new Cat();

    }
}
//接口通常提取的是行为动作
interface Flyable{
    void fly();
}

class Animal{

}

class Cat extends Animal implements Flyable{
    @Override
    public void fly() {
        System.out.println("猫起飞");
    }
}