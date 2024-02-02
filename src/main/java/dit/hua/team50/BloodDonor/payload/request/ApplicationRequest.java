package dit.hua.team50.BloodDonor.payload.request;

import jakarta.validation.constraints.NotBlank;

public class ApplicationRequest {
    @NotBlank
    private String recent_blood_tests;

    public String getRecent_blood_tests() {
        return recent_blood_tests;
    }

    public void setRecent_blood_tests(String recent_blood_tests) {
        this.recent_blood_tests = recent_blood_tests;
    }
}
