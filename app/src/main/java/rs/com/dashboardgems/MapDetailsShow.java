package rs.com.dashboardgems;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by yasar on 11/11/17.
 */

public class MapDetailsShow extends BaseObservable {
    private String schoolName;
    private String totalStudents;
    private String revenueforcurrentyear;
    private String expenses;
    private String totalProfit;
    private String vacantPositions;

    @Bindable
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        notifyPropertyChanged(BR.schoolName);
    }

    @Bindable
    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
        notifyPropertyChanged(BR.totalStudents);
    }

    @Bindable
    public String getRevenueforcurrentyear() {
        return revenueforcurrentyear;
    }

    public void setRevenueforcurrentyear(String revenueforcurrentyear) {
        this.revenueforcurrentyear = revenueforcurrentyear;
        notifyPropertyChanged(BR.revenueforcurrentyear);
    }

    @Bindable
    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
        notifyPropertyChanged(BR.expenses);
    }

    @Bindable
    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
        notifyPropertyChanged(BR.totalProfit);
    }

    @Bindable
    public String getVacantPositions() {
        return vacantPositions;
    }

    public void setVacantPositions(String vacantPositions) {
        this.vacantPositions = vacantPositions;
        notifyPropertyChanged(BR.vacantPositions);
    }

    @Bindable
    public String getTeacherStudentRatio() {
        return teacherStudentRatio;
    }


    public void setTeacherStudentRatio(String teacherStudentRatio) {
        this.teacherStudentRatio = teacherStudentRatio;
        notifyPropertyChanged(BR.teacherStudentRatio);
    }

    private String teacherStudentRatio;
}
