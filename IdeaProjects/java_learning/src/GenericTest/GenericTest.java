package GenericTest;

import java.util.ArrayList;
import java.util.Collection;

public class GenericTest {
    /*
    * After JDK, java introduces generic mechanism
    * ArrayList<>(), When we create an ArrayList object, we should fill in type in the angle brackets before jdk8
    * There is an automatic type judgement mechanism after jdk8, so we don't need to fill in type
    *
    * if we fill type in the angle brackets, we can only add matched element into the collection,
    * for example, there is an ArrayList<Animal> object, we can add Animal object or subclass such as
    * Cat, Dog, Bird...
    * if we add other type object like String object, there will be a warning called "unmatched type"
    *
    * Automatic type judgement also called diamond expression
    *
    * */
    public static void main(String[] args) {

    }
}

/*
* We can also create custom generic class, we only need to add angle brackets behind the name of type
* Usually, we use "E" or "T" to replace generic type
* E means element
* T means type
*
* Java has more grammar about generic paradigm like "?", just learning by myself
* */

class GenericTest02<E> {
    public static void main(String[] args) {
        //we specify an object of type String
        GenericTest02<String> gt = new GenericTest02();

        gt.m1("123");
//        gt.m1(123); //report error
    }

    public void m1(E e) {
        System.out.println(e);
    }
}