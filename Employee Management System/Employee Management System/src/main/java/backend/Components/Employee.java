package backend.Components;

public class Employee {

    private String empId;
    private String depId;
    private String empName;
    private String empGmail;
    private String department;
    private String password;

    public Employee(String empId, String depId, String empName, String empGmail, String department, String password) {
        this.empId = empId;
        this.depId = depId;
        this.empName = empName;
        this.empGmail = empGmail;
        this.department = department;
        this.password = password;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", depId='" + depId + '\'' +
                ", empName='" + empName + '\'' +
                ", empGmail='" + empGmail + '\'' +
                ", department='" + department + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
