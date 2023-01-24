package Collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ContainerTest {
    public static void main(String[] args) {
/*
        Vector<Integer> vector = new Vector<>();//Hardly used, because it uses synchronized mechanism, inefficient
        HashMap<Integer,Integer> mp = new HashMap<>();
        TreeMap<Integer,Integer> mp1 = new TreeMap<>();
        Queue<Integer> queue = new Queue<>();
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
*/
//        Collection c = new ArrayList();
/*
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
*/
        /*
        * 1.Method contains() comparing by content, so the object stored in container should override the method equals()
        * 2.Any custom element type as the parameter of collections should override both hashCode() and equals() by generating through IDEA
        * */
    }
}

class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        /*
         * How to choose the two collections
         * Advantages of ArrayList:
         * 1.Depends on the underlying array implementation,
         * we can access the element through the index,
         * which makes ArrayList efficient in inserting element at the end of it and quickly finding element
         * 2.ArrayList is thread-safe means it use synchronized mechanism
         *
         * Disadvantages:
         * 1.Can't store a large number of elements, because it needs a large of continuous memory space
         * 2.Low efficiency in random insertion elements and removing elements
         *
         * Advantages of LinkList:
         * 1.Depends on the underlying linked list implementation,
         * the linklist has efficient random insertion of element.
         * 2.LinkList can store a large number of elements by using discontinuous memory space
         *
         * Disadvantages:
         * 1.Low efficiency in finding element
         * */

        list.add(1);
        // Access element through index
        System.out.println(list.get(0));
        for (Object i: list) System.out.println(i);
    }
}

class SetTest {
    /*
     * About HashSet and TreeSet
     * HashSet:
     * 1.When we create a HashSet with not specifying element type,
     * the set can add any type of element at the same time
     * 2.the Hashset only automatically sorts basic type elements such as Integer, String...
     *
     *
     * TreeSet:
     * 1.Even if we create a TreeSet with not specifying element type,
     * the collection only can add one type of element
     * 2.The underlying implementation of TreeSet is actually TreeMap.
     * 3.TreeSet can't sort custom type element,
     * if we don't implement the interface Comparable and override method compareTo().
     * 4.The TreeSet is SortedSet, therefore, the TreeSet will sort elements automatically
     * if the element implements comparable or pass in parameter Comparator in the constructor of TreeSet
     *
     *
     * There are two ways to implement comparable rules
     * 1.implement Comparable and override compareTo() method
     * 2.Construct a Comparator as the parameter of the constructor of TreeSet
     * For example
     *
     * */

    public static class Student implements Comparable<Student>{
        private int age;

        public Student(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Student student) {
            /*
            * Ascending order
            * return student.age - this.age;
            * */
            //Descending order
            return student.age - this.age;
        }

        @Override
        public String toString() {
            return age + "";
        }
    }

    public static class StudentComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }


    public static void main(String[] args) {
//        Set<String> st = new HashSet<>();

//        for(String i: st) System.out.println(i);

        Set<Student> st = new TreeSet(new StudentComparator()); //Another way to implement
        Set<Student> set = new HashSet<>();
        st.add(new Student(10));
        st.add(new Student(9));
        st.add(new Student(7));
        st.add(new Student(4));

        for (Student student : st) {
            System.out.println(student);
        }
    }



}

class MapTest {
    public static void main(String[] args) {
        Map mp = new HashMap<>();
        mp.put(1, "ZhangSan");
        mp.put(2, "LiSi");
        mp.put(3, "WangWu");
        mp.put(4, "ZhaoLiu");


        /*
        * The iteration of Map, just remember it
        * */

        Set set = mp.keySet();
        for (Object obj : set) {
            System.out.println(obj + "->" + mp.get(obj));
        }


        Set<Map.Entry> st = mp.entrySet();
        System.out.println(st.size());
        for(Map.Entry i: st) System.out.println(i.getKey() + "-->" + i.getValue());
        /*
        * About HashMap:
        * 1.Improper use of HashMap will cause uneven hash,
        * because the bottom layer implemented by linked list and hashtable.
        * 2.If method hashCode() always return true,
        * the HashMap will degenerate into linked list.
        * 3.If method hashCode() always return false,
        * the HashMap will degenerate into array.
        * 4.Both cases called uneven hash.
        *
        * Key Point:
        * 1.For the object as the key in the HashMap and the object as the value in the HashSet,
        * we need to override both hashCode() and equals()
        * 2.Use IDEA to generate both method
        *
        * About initialization of HashMap:
        * 1.Default capacity = 16, Default loading factor = 0.75
        * When the size of underlying array reaches 75% capacity, the array will automatic expansion
        * 2.The key can be null
        * 3.the default initial capacity of HashMap must be the power of 2,
        * which can increase the efficiency of the collection
        *
        * JDK8 feature:
        * When the linked list longer than 8, the list will automatically turn into binary tree or red-black tree,
        * if the node of tree less than 6, the tree will degeneration into linked list again
        *
        *
        * */
    }
}

class PropertiesTest {
    /*
    * 1."Properties" is a Map, but the key and value both are String
    * 2.Combined use of IO and Properties
    * */
    public static void main(String[] args) throws IOException {
        Properties pt = new Properties();
        FileReader fr = new FileReader("UserInformation.properties");
        pt.load(fr);
        /*
        * Suggestions:
        * 1.Use Properties in this way, we can easily extract the information from the file,
        * the Properties will automatically match both sides of equal sign,
        * the left as the key, the other side as the value
        * 2.In the future, we will encounter the situation that the date will be modified frequently,
        * so we can use ".properties" file to store the data except modifying our code.
        *
        * ".properties" file called property config file
        * */
        System.out.println(pt.getProperty("userName"));
        System.out.println(pt.getProperty("password"));
        fr.close();
    }
}

class HashtableTest{
    public static void main(String[] args) {
        Hashtable<Integer, Integer> ht = new Hashtable();

    }
}