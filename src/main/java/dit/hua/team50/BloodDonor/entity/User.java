package dit.hua.team50.BloodDonor.entity;

public class User {

    private Integer Id;

    private String fname;

    private String lname;

    private String phone_number;

    private String email;

    private String username;

    private String password;

    public User() {
    }

    public User(Integer id, String fname, String lname, String phone_number, String email, String username, String password) {
        Id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
