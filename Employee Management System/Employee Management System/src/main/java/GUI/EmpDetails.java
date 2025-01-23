package GUI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpDetails {

    @JsonProperty("employee_id")
    private String empId;

    @JsonProperty("department_id")
    private String depId;

    @JsonProperty("employee_name")
    private String empName;

    @JsonProperty("employee_gmail")
    private String empGmail;

    @JsonProperty("description")
    private String description;

    @JsonProperty("adminReview")
    private String review;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpGmail() {
        return empGmail;
    }

    public void setEmpGmail(String empGmail) {
        this.empGmail = empGmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
