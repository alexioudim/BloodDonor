package dit.hua.team50.BloodDonor.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @Column
    private Date date_of_birth;
    @Column
    private String address;
    @Column
    private String blood_type;
    @Column
    private String recent_blood_tests;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

//    public Citizen(Integer id, String fname, String lname, String phone_number, String email, Date date_of_birth, String address, String blood_type, String recent_blood_tests, String username, String password) {
//        Id = id;
//        this.date_of_birth = date_of_birth;
//        this.address = address;
//        this.blood_type = blood_type;
//        this.recent_blood_tests = recent_blood_tests;
//    }

    public Citizen(String fname, String lname, String phone_number, String email, Date date_of_birth, String address, String blood_type, String recent_blood_tests, String username, String password) {
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.blood_type = blood_type;
        this.recent_blood_tests = recent_blood_tests;
    }


    public Citizen() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getRecent_blood_tests() {
        return recent_blood_tests;
    }

    public void setRecent_blood_tests(String recent_blood_tests) {
        this.recent_blood_tests = recent_blood_tests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "Id=" + Id +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", recent_blood_tests='" + recent_blood_tests + '\'' +
                '}';
    }
}
