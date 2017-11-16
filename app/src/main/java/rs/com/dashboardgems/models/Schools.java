package rs.com.dashboardgems.models;

/**
 * Created by yasar on 10/11/17.
 */

public class Schools {
    private String name;
    private String shortName;

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    private boolean isTrue = false;
    private int resourceID;

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public Schools(String name, String shortName, boolean isTrue, boolean isFlag) {
        this.name = name;
        this.shortName = shortName;
        this.isTrue = isTrue;
        this.isFlag = isFlag;
    }

    private boolean isFlag = false;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Schools(String name, String shortName, boolean isTrue) {
        this.name = name;
        this.shortName = shortName;
        this.isTrue = isTrue;
    }

    public Schools(String name, String shortName) {

        this.name = name;
        this.shortName = shortName;
    }

    public Schools(String name) {
        this.name = name;

    }


    public Schools(String name, boolean isTrue, int resourceID, boolean isFlag) {
        this.name = name;
        this.isTrue = isTrue;
        this.resourceID = resourceID;
        this.isFlag = isFlag;
    }

    public Schools(String name, boolean isTrue, boolean isFlag) {
        this.name = name;

        this.isTrue = isTrue;
        this.isFlag = isFlag;
    }

    public Schools(String name, boolean isTrue) {
        this.name = name;

        this.isTrue = isTrue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
