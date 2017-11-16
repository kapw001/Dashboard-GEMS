package rs.com.dashboardgems.models;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by yasar on 15/11/17.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    public boolean isRed() {
        return isRed;
    }

    private final BitmapDescriptor mIcon;
    private final boolean isRed;

//    public MyItem(double lat, double lng) {

    public BitmapDescriptor getIcon() {
        return mIcon;
    }

//        mPosition = new LatLng(lat, lng);
//    }

    public MyItem(double lat, double lng, String title, String snippet, BitmapDescriptor icon, boolean isRedT) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        mIcon = icon;
        isRed = isRedT;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}