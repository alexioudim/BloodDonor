package dit.hua.team50.BloodDonor.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "citizen")
    private List<Donation> donations = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "Id=" + Id +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", user=" + user +
                ", donations=" + donations +
                '}';
    }
}
