package dit.hua.team50.BloodDonor.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactInfoRequest {
    @NotBlank
    @Column
    private String phone_number;
    @Email
    @Column
    private String email;


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
}
