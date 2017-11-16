package rs.com.dashboardgems.model;

/**
 * Created by yasar on 4/8/17.
 */

public class VisitorInfo {
    private String name, visitorType, isInorOut, imageUrl;
    private boolean isCheckBoxClicked = false;

    public boolean isCheckBoxClicked() {
        return isCheckBoxClicked;
    }

    public void setCheckBoxClicked(boolean checkBoxClicked) {
        isCheckBoxClicked = checkBoxClicked;
    }

    public VisitorInfo(String name, String visitorType, String isInorOut, String imageUrl) {
        this.name = name;
        this.visitorType = visitorType;
        this.isInorOut = isInorOut;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public String getIsInorOut() {
        return isInorOut;
    }

    public void setIsInorOut(String isInorOut) {
        this.isInorOut = isInorOut;
    }
}
