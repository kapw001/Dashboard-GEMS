package rs.com.dashboardgems;

/**
 * Created by yasar on 11/11/17.
 */

public class MarkerData {

    private double lat;
    private double log;

    public double getLat() {
        return lat;
    }

    public MarkerData(double lat, double log) {
        this.lat = lat;
        this.log = log;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }


}
