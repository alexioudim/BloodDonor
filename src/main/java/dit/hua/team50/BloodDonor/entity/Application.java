package dit.hua.team50.BloodDonor.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Integer id;

    @Column
    private Date date_created;

    @Column
    private String recent_blood_tests;
    @Column
    private Boolean approval_status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    public Application() {
    }

    public Application(Date date_created, String recent_blood_tests, Boolean approval, Citizen citizen) {
        this.date_created = date_created;
        this.recent_blood_tests = recent_blood_tests;
        this.approval_status= approval;
        this.citizen = citizen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
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

    public Boolean getApprovalStatus() {
        return approval_status;
    }

    public void setApprovalStatus(Boolean approval_status) {
        this.approval_status = approval_status;
    }
}
