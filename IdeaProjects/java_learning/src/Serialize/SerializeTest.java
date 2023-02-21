package Serialize;

import java.io.*;

public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        * 1.Use ObjectOutputStream to store the status of object in our disk.
        * 2.Usually, we use List to store our object.
        * 3.If we don't want the field participate in serialization,
        * we can use keyword "transient(游离的)" to modify it.
        *
        * */

        /*List<Student> list = new ArrayList<>();
        list.add(new Student(5, "ZhangSan"));
        list.add(new Student(4, "LiSi"));
        list.add(new Student(5, "WangWu"));*/

        Object[] obj = new Object[5];
        obj[0] = new Student(123, "ZhangSan",2);
        obj[1] = new Student(123, "LiSi",3);
        obj[2] = new Student(123, "ZhangSan",4);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student"));
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        /*
        * 1.Use ObjectInputStream to deserialize
        * */

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student"));
//        System.out.println(ois.readObject());
//        List<Student> obj = new ArrayList<>();
//        obj = (List<Student>) ois.readObject();
        Object[] obj1 = (Object[]) ois.readObject();
        for (Object obj2 : obj1) {
            System.out.println(obj2);
        }
        ois.close();

    }
}

/*
 * 1.If we want to serialize an object,
 * we should implement the interface Serializable
 * 2.The mechanism of Java to distinguish class from serialization version number.
 * 3.Serializable is a symbolic interface,
 * the purpose is to make JVM generate a serialized version number.
 * Advantages of automatically generate serialized version number:
 * 1.JVM can automatically distinguish different class
 * Disadvantages:
 * 2.If we have serialized Object,
 * the code of class can not be modified again,
 * unless we define a final static field name "serializeVersionUID".
 *
 * Suggestions:
 * 1.If we required for serialized objects,
 * we should better assign serialVersionUID in advance.
 *
 * For example "public static final long serialVersionUID = 2135135435565123L"
 *
 * */
class Student implements Serializable{
    //Set serialVersionUID
    public static final long serialVersionUID = 2135135435565123L;

    private int age;
    private String name;
    //property will not participate in serialization
    private transient int group;

    public Student() {
    }

    public Student(int age, String name) {
        this(age, name, 1);
    }

    public Student(int age, String name, int group) {
        this.age = age;
        this.name = name;
        this.group = group;
    }


    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}