package ArrayTest;
import Pet.*;

public class ArrayTest01 {
    public static void main(String[] args) {
        Pet[] pets = new Pet[5];
        pets[1] = new Cat();
        pets[2] = new Dog();
        for(Pet i: pets) System.out.println(i);
    }

}
