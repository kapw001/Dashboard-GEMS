
package rs.com.dashboardgems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("school_list")
    @Expose
    private List<SchoolList> schoolList = null;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("incident_id")
    @Expose
    private Integer incidentId;

    public String getIncident_name() {
        return incident_name;
    }

    public void setIncident_name(String incident_name) {
        this.incident_name = incident_name;
    }

    @SerializedName("incident_name")
    @Expose

    private String incident_name;


    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("people_data")
    @Expose
    private List<PersonData> peopleData = null;

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public List<PersonData> getPeopleData() {
        return peopleData;
    }

    public void setPeopleData(List<PersonData> peopleData) {
        this.peopleData = peopleData;
    }

    private final static long serialVersionUID = 4899844440364999800L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<SchoolList> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolList> schoolList) {
        this.schoolList = schoolList;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
