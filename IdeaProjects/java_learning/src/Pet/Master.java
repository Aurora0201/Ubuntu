package Pet;

public class Master {
    public static void feed(Pet pet){
        pet.eat();
    }
    public static void main(String[] args){
        Cat c = new Cat();
        Dog d = new Dog();

        feed(d);
        feed(c);

    }

}
