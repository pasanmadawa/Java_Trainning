package GUI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminObject {

    @JsonProperty("admin_id")
    private String adminId;

    @JsonProperty("department_id")
    private String depId;

    @JsonProperty("admin_name")
    private String adminName;

    @JsonProperty("admin_gmail")
    private String adminGmail;

    public String  getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminGmail() {
        return adminGmail;
    }

    public void setAdminGmail(String adminGmail) {
        this.adminGmail = adminGmail;
    }
}
