package rs.com.dashboardgems.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 11/11/17.
 */

public class SchoolDetailsResult implements Serializable {

    @SerializedName("regionName")
    @Expose
    private String regionName;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("SchoolList")
    @Expose
    private List<SchoolList> schoolList = null;
    private final static long serialVersionUID = -755535860184906246L;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<SchoolList> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolList> schoolList) {
        this.schoolList = schoolList;
    }

}