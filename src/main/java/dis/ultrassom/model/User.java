package dis.ultrassom.model;

public class User {
    private String userName;

    public User(String name) {
        this.userName = name;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }
}