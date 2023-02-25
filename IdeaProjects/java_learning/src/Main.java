import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Student());
    }
}

class Student{
    private String name;
    private Address address;

    public Student() {
        name = "ZhangSan";
        address = new Address();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address{
    private String country;
    private String province;

    public Address() {
        country = "China";
        province = "GuangXi";
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}