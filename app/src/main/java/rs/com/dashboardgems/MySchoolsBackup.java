package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rs.com.dashboardgems.databinding.MyschoolsBinding;
import rs.com.dashboardgems.models.SchoolDetailsResult;
import rs.com.dashboardgems.models.SchoolList;

import static rs.com.dashboardgems.Utils.getSchoolDetailsResultList;

/**
 * Created by yasar on 9/11/17.
 */

public class MySchoolsBackup extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationChangeListener {
    private static final String TAG = "MySchools";
    private MyschoolsBinding binding;
    private GoogleMap mMap;
    private Map<String, MarkerData> mapLatlog = new HashMap<>();
    private List<SchoolDetailsResult> list = new ArrayList<>();
    private MapDetailsShow mapDetailsShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.myschools, container, false);
        View view = binding.getRoot();


        list.addAll(getSchoolDetailsResultList(getActivity()));


        mapDetailsShow = new MapDetailsShow();


        binding.setMds(mapDetailsShow);

        mapLatlog.put("uk", new MarkerData(55.378051, -3.435973));

        mapLatlog.put("india", new MarkerData(20.593684, 78.962880));

        mapLatlog.put("uae", new MarkerData(23.424076, 53.847818));


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        mapDetailsShow.setSchoolName("GEMS Twickenham Primary Academy");
//        mapDetailsShow.setTotalStudents("67");
//        mapDetailsShow.setRevenueforcurrentyear("89");
//        mapDetailsShow.setExpenses("78");
//        mapDetailsShow.setTotalProfit("64");
//        mapDetailsShow.setVacantPositions("24");
//        mapDetailsShow.setTeacherStudentRatio("34%");

        return view;
    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet, int iconResID, boolean isShowInfo) {

        Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .anchor(0.5f, 0.5f)
                        .title(title.toUpperCase())
                        .snippet(snippet)
//                .icon(BitmapDescriptorFactory.fromResource(iconResID)))
        );

        if (isShowInfo) {
//            marker.showInfoWindow();
        }

        return marker;
    }

    int pos = 0;

    @Override
    public boolean onMarkerClick(Marker marker) {
        List<SchoolList> slist = null;

        switch (marker.getTitle().toLowerCase()) {

//            case "united kingdom":
//                mMap.clear();
//                pos = 1;
//                slist = list.get(1).getSchoolList();
//
//                for (int i = 0; i < slist.size(); i++) {
//
//                    SchoolList schoolList = slist.get(i);
//
//                    createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);
//                }
//
//                mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));
//                break;
//            case "india":
//                mMap.clear();
//                pos = 0;
//                slist = list.get(0).getSchoolList();
//                for (int i = 0; i < slist.size(); i++) {
//
//                    SchoolList schoolList = slist.get(i);
//
//                    createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);
//                }
////                createMarker(23.424076, 53.847818, "Work", "", 1);
//                mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));
//                break;
//            case "uae":
//                pos = 2;
//                mMap.clear();
//                slist = list.get(2).getSchoolList();
//
//                for (int i = 0; i < slist.size(); i++) {
//
//                    SchoolList schoolList = slist.get(i);
//
//                    createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);
//                }
//
////                createMarker(23.424076, 53.847818, "Work", "", 1);
//                mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));
//                break;
//
//            case "usa":
//                pos = 3;
//                mMap.clear();
//                slist = list.get(3).getSchoolList();
//
//                for (int i = 0; i < slist.size(); i++) {
//
//                    SchoolList schoolList = slist.get(i);
//
//                    createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);
//                }
//
////                createMarker(23.424076, 53.847818, "Work", "", 1);
//                mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));
//                break;
//
//            case "france":
//                pos = 4;
//                mMap.clear();
//                slist = list.get(4).getSchoolList();
//
//                for (int i = 0; i < slist.size(); i++) {
//
//                    SchoolList schoolList = slist.get(i);
//
//                    Log.e(TAG, "onMarkerClick: France " + schoolList.getLatitude() + "  " + schoolList.getLongitude() + "    " + schoolList.getName());
//
//                    createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);
//                }
//
////                createMarker(23.424076, 53.847818, "Work", "", 1);
//                mMap.moveCamera(CameraUpdateFactory.zoomBy(3f));
//                break;

            default:
                final float scale = getContext().getResources().getDisplayMetrics().density;
                int px = (int) (300 * scale + 0.5f);
                binding.maplayout.getLayoutParams().height = px;
//        relativeLayout.getLayoutParams().width = 100;

                int p = getPos(list.get(pos).getSchoolList(), marker.getTitle().toString());

                SchoolList schoolList = list.get(pos).getSchoolList().get(p);
                showDetails(schoolList);

                break;


        }


        return false;
    }

    private int getPos(List<SchoolList> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                return i;
            }
        }

        return 0;
    }


    private void showDetails(SchoolList schoolList) {
        mapDetailsShow.setSchoolName(schoolList.getName());
        mapDetailsShow.setTotalStudents(String.valueOf(schoolList.getTotalStudent()));
        mapDetailsShow.setRevenueforcurrentyear(String.valueOf(schoolList.getRevenue()) + " Mn");
        mapDetailsShow.setExpenses(String.valueOf(schoolList.getExpense()) + " Mn");

        double r = Double.parseDouble(String.valueOf(schoolList.getRevenue()));
        double e = Double.parseDouble(String.valueOf(schoolList.getExpense()));

        double t = r - e;

        long tt = Math.round(t);

        mapDetailsShow.setTotalProfit(String.valueOf(tt) + "%");

        Random random = new Random();
        int n = random.nextInt(100);

        mapDetailsShow.setVacantPositions(String.valueOf(n));
//        int n1 = random.nextInt(10);

        mapDetailsShow.setTeacherStudentRatio("12:1");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMyLocationChangeListener(this);

        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CameraPosition cameraPosition = mMap.getCameraPosition();
                Log.e(TAG, "onCameraMove: " + cameraPosition.zoom);
                if (cameraPosition.zoom > 5.0) {
                    zoomInData();
//                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    markerReset();
//                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });


        markerReset();


//        for (Map.Entry<String, MarkerData> entry : mapLatlog.entrySet()) {
//
//            createMarker(entry.getValue().getLat(), entry.getValue().getLog(), entry.getKey(), "", 1);
//        }

    }


    private void zoomInData() {
//        int p = getPos(list.get(pos).getSchoolList(), marker.getTitle().toString());

        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j < list.get(i).getSchoolList().size(); j++) {

                SchoolList schoolList = list.get(i).getSchoolList().get(j);

                Log.e(TAG, "onMarkerClick: France " + schoolList.getLatitude() + "  " + schoolList.getLongitude() + "    " + schoolList.getName());

                createMarker(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), "", 1, false);

            }


        }
    }

    private List<LatLng> mainPoint = new ArrayList<>();

    public void markerReset() {
        mMap.clear();
        binding.maplayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        binding.maplayout.getLayoutParams().height = RelativeLayout.LayoutParams.MATCH_PARENT;
        for (int i = 0; i < list.size(); i++) {

            SchoolDetailsResult schoolDetailsResult = list.get(i);

            createMarker(schoolDetailsResult.getLatitude(), schoolDetailsResult.getLongitude(), schoolDetailsResult.getRegionName(), "", 1, true);

            mainPoint.clear();
            mainPoint.add(new LatLng(schoolDetailsResult.getLatitude(), schoolDetailsResult.getLongitude()));

        }

//        mMap.moveCamera(CameraUpdateFactory.zoomBy(-3f));
    }


    @Override
    public void onMyLocationChange(Location location) {
        Location target = new Location("target");
        for (LatLng point : mainPoint) {
            target.setLatitude(point.latitude);
            target.setLongitude(point.longitude);

            if (location.distanceTo(target) < 20) {
                // bingo!
                Log.e(TAG, "onMyLocationChange: nearby distance   " + location.distanceTo(target));
            }
        }
    }
}
