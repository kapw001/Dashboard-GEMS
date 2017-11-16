package rs.com.dashboardgems.model;

/**
 * Created by yasar on 21/8/17.
 */

public class TabMenu {
    private String tabName;
    private String totalCount;


    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTotalCount() {

        return totalCount;
    }

    public TabMenu(String tabName, String totalCount) {
        this.tabName = tabName;
        this.totalCount = totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
