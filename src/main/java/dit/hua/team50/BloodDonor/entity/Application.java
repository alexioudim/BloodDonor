package dit.hua.team50.BloodDonor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "Applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer id;

    @Column
    private LocalDate date_created;

    @Column
    private String recent_blood_tests;
    @Column
    private String approval_status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "citizen_id")
    @JsonIgnore
    private Citizen citizen;

    public Application(LocalDate date_created, String recent_blood_tests, String approval_status, Citizen citizen) {
        this.date_created = date_created;
        this.recent_blood_tests = recent_blood_tests;
        this.approval_status = approval_status;
        this.citizen = citizen;
    }

    public Application() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public String getRecent_blood_tests() {
        return recent_blood_tests;
    }

    public void setRecent_blood_tests(String recent_blood_tests) {
        this.recent_blood_tests = recent_blood_tests;
    }

    public String getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }
}
