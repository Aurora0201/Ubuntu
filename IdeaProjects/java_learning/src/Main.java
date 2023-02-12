public class Main {
    private String name;
    private boolean gender;
    private String sNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public Main() {
    }

    public Main(String name, boolean gender, String sNo) {
        this.name = name;
        this.gender = gender;
        this.sNo = sNo;
    }

    public static void main(String[] args) {

    }
}


