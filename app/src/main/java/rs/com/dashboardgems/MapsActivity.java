package rs.com.dashboardgems;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private Map<String, MarkerData> mapLatlog = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapLatlog.put("uk", new MarkerData(55.378051, -3.435973));

        mapLatlog.put("india", new MarkerData(20.593684, 78.962880));

        mapLatlog.put("uae", new MarkerData(23.424076, 53.847818));


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        mMap.setOnMarkerClickListener(this);
        for (Map.Entry<String, MarkerData> entry : mapLatlog.entrySet()) {

            createMarker(entry.getValue().getLat(), entry.getValue().getLog(), entry.getKey(), "", 1);
        }

//        mMap.moveCamera(CameraUpdateFactory.newLatLng());
//        mMap.moveCamera(CameraUpdateFactory.);
    }


    protected Marker createMarker(double latitude, double longitude, String title, String snippet, int iconResID) {

        return mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .anchor(0.5f, 0.5f)
                        .title(title.toUpperCase())
                        .snippet(snippet)
//                .icon(BitmapDescriptorFactory.fromResource(iconResID)))
        );
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        switch (marker.getTitle().toLowerCase()) {

            case "uk":
                mMap.clear();
                createMarker(23.424076, 53.847818, "Work", "", 1);

                break;
            case "india":
                mMap.clear();
                createMarker(23.424076, 53.847818, "Work", "", 1);

                break;
            case "uae":
                mMap.clear();
                createMarker(23.424076, 53.847818, "Work", "", 1);

                break;
        }

        mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));


        return false;
    }
}
