package ReflexTest;

public class User {
    private String name;
    private String password;
    public boolean login(String name, String password) {
        boolean ok = this.name.equals(name) && this.password.equals(password);
        System.out.println("Login " + (ok ? "successful" : "failed"));
        return ok;
    }

    public User() {
        name = "123";
        password = "123";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logout() {
        System.out.println("Logout Successful");
    }
}
