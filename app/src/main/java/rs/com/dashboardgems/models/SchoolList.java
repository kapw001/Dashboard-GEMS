package rs.com.dashboardgems.models;

import java.io.Serializable;

/**
 * Created by yasar on 11/11/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SchoolList implements Serializable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Revenue")
    @Expose
    private Double revenue;
    @SerializedName("Budget")
    @Expose
    private Double budget;
    @SerializedName("Expense")
    @Expose
    private Double expense;
    @SerializedName("Retention")
    @Expose
    private Integer retention;
    @SerializedName("New_Enrollment")
    @Expose
    private Integer newEnrollment;
    @SerializedName("Total_Student")
    @Expose
    private Integer totalStudent;
    private final static long serialVersionUID = 3919828864883872287L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Integer getRetention() {
        return retention;
    }

    public void setRetention(Integer retention) {
        this.retention = retention;
    }

    public Integer getNewEnrollment() {
        return newEnrollment;
    }

    public void setNewEnrollment(Integer newEnrollment) {
        this.newEnrollment = newEnrollment;
    }

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }

}