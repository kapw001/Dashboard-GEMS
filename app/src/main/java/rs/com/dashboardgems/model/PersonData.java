package rs.com.dashboardgems.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 18/8/17.
 */

public class PersonData implements Serializable {

    @SerializedName("stud_class")
    @Expose
    public String studClass;

    private int schoolId;

    public int getSchoolId() {
        return schoolId;
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "studClass='" + studClass + '\'' +
                ", schoolId=" + schoolId +
                ", photo=" + photo +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", isSafe=" + isSafe +
                ", server_is_safe_updated=" + server_is_safe_updated +
                '}';
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public PersonData() {

    }

    public PersonData(int schoolId, String studClass, Object photo, String name, String type, Integer id, Boolean isSafe, Boolean server_is_safe_updated) {
        this.studClass = studClass;
        this.schoolId = schoolId;
        this.photo = photo;
        this.name = name;
        this.type = type;
        this.id = id;
        this.isSafe = isSafe;
        this.server_is_safe_updated = server_is_safe_updated;
    }
//    public PersonData(String studClass, Object photo, String name, String type, Integer id, Boolean isSafe, Boolean server_is_safe_updated) {
//        this.studClass = studClass;
//        this.photo = photo;
//        this.name = name;
//        this.type = type;
//        this.id = id;
//        this.isSafe = isSafe;
//        this.server_is_safe_updated = server_is_safe_updated;
//    }

    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_safe")
    @Expose
    private Boolean isSafe;
    private final static long serialVersionUID = -8694332301894578513L;

    private Boolean server_is_safe_updated = false;

    public Boolean getServer_is_safe_updated() {
        return server_is_safe_updated;
    }

    public void setServer_is_safe_updated(Boolean server_is_safe_updated) {
        this.server_is_safe_updated = server_is_safe_updated;
    }

    public String getStudClass() {
        return studClass;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsSafe() {
        return isSafe;
    }

    public void setIsSafe(Boolean isSafe) {
        this.isSafe = isSafe;
    }

}
