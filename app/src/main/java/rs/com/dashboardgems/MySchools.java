package rs.com.dashboardgems;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rs.com.dashboardgems.databinding.AnalyticsBinding;
import rs.com.dashboardgems.databinding.MyschoolsBinding;
import rs.com.dashboardgems.models.MyItem;
import rs.com.dashboardgems.models.SchoolDetailsResult;
import rs.com.dashboardgems.models.SchoolList;

import static rs.com.dashboardgems.Utils.getSchoolDetailsResultList;

/**
 * Created by yasar on 9/11/17.
 */

public class MySchools extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private static final String TAG = "MySchools";
    private MyschoolsBinding binding;
    private GoogleMap mMap;
    private Map<String, MarkerData> mapLatlog = new HashMap<>();
    private List<SchoolDetailsResult> list = new ArrayList<>();
    private MapDetailsShow mapDetailsShow;

    private ClusterManager<MyItem> mClusterManager;


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

    @Override
    public boolean onMarkerClick(Marker marker) {


        Log.e(TAG, "onMarkerClick: " + marker.getPosition());


        if (marker.getTitle() != null) {

            try {

                Log.e(TAG, "onMarkerClick: " + marker.getTitle());

                final float scale = getContext().getResources().getDisplayMetrics().density;
                int px = (int) (300 * scale + 0.5f);
                binding.maplayout.getLayoutParams().height = px;
//        relativeLayout.getLayoutParams().width = 100;

                int p = getPos(marker.getTitle().toString(), false);

//            SchoolList schoolList = list.get(pos).getSchoolList().get(p);
//            showDetails(schoolList);

            } catch (NullPointerException e) {

                Log.e(TAG, "onMarkerClick: " + e.getMessage());

            }
        }


        return false;
    }

    private int getPos(String name, boolean isRed) {
        for (int i = 0; i < list.size(); i++) {

            List<SchoolList> list1 = list.get(i).getSchoolList();

            for (int j = 0; j < list1.size(); j++) {
                SchoolList schoolList = list1.get(j);
                if (schoolList.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                    showDetails(schoolList, isRed);
                }
            }

        }

        return 0;
    }


    private void showDetails(SchoolList schoolList, boolean isRed) {


        CustomBottomSheetDialogFragment.getInstance(schoolList, isRed).show(getChildFragmentManager(), "Dialog");

//        new CustomBottomSheetDialogFragment().show(getChildFragmentManager(), "Dialog");


//        mapDetailsShow.setSchoolName(schoolList.getName());
//        mapDetailsShow.setTotalStudents(String.valueOf(schoolList.getTotalStudent()));
//        mapDetailsShow.setRevenueforcurrentyear(String.valueOf(schoolList.getRevenue()) + " Mn");
//        mapDetailsShow.setExpenses(String.valueOf(schoolList.getExpense()) + " Mn");
//
//        double r = Double.parseDouble(String.valueOf(schoolList.getRevenue()));
//        double e = Double.parseDouble(String.valueOf(schoolList.getExpense()));
//
//        double t = r - e;
//
//        long tt = Math.round(t);
//
//        mapDetailsShow.setTotalProfit(String.valueOf(tt) + "%");
//
//        Random random = new Random();
//        int n = random.nextInt(100);
//
//        mapDetailsShow.setVacantPositions(String.valueOf(n));
////        int n1 = random.nextInt(10);
//
//        mapDetailsShow.setTeacherStudentRatio("12:1");
//
//        if (isRed) {
//            binding.expenseTitle.setTextColor(Color.RED);
//            binding.expenses.setTextColor(Color.RED);
//        } else {
//            binding.expenseTitle.setTextColor(Color.BLACK);
//            binding.expenses.setTextColor(Color.BLACK);
//            binding.expenseTitle.setAlpha(.5f);
//            binding.expenses.setAlpha(.5f);
//        }
    }

    //Initialize to a non-valid zoom value
    private float previousZoomLevel = -1.0f;
    private boolean isZooming = false;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


//        mMap.setOnMarkerClickListener(this);

        mClusterManager = new ClusterManager<MyItem>(getActivity(), mMap);

        mClusterManager.setRenderer(new ClusterRenderer(getActivity(), mMap, mClusterManager));
//        mClusterManager.setRenderer(new CustomClusterRenderer(getActivity(), mMap, mClusterManager));

        mMap.setOnCameraIdleListener(mClusterManager);
//        mMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem myItem) {

                Log.e(TAG, "onClusterItemClick: " + myItem.getTitle());


                if (myItem.getTitle() != null) {

                    try {

                        Log.e(TAG, "onMarkerClick: " + myItem.getTitle());

//                        final float scale = getContext().getResources().getDisplayMetrics().density;
//                        int px = (int) (300 * scale + 0.5f);
//                        binding.maplayout.getLayoutParams().height = px;
//        relativeLayout.getLayoutParams().width = 100;

                        Log.e(TAG, "onClusterItemClick: iMga " + myItem.getIcon());


                        int p = getPos(myItem.getTitle(), myItem.isRed());
//                        if (myItem.getIcon().equals(BitmapDescriptorFactory.defaultMarker(
//                                BitmapDescriptorFactory.HUE_RED))) {
//                            int p = getPos(myItem.getTitle(), true);
//                        } else {
//                            int p = getPos(myItem.getTitle(), false);
//                        }


//            SchoolList schoolList = list.get(pos).getSchoolList().get(p);
//            showDetails(schoolList);

                    } catch (NullPointerException e) {

                        Log.e(TAG, "onMarkerClick: " + e.getMessage());

                    }
                }


//                mMap.getMarker(myItem).showInfoWindow();
                return false;
            }
        });
        mMap.setOnMarkerClickListener(mClusterManager);

//        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
//            @Override
//            public void onCameraChange(CameraPosition position) {
//                Log.d("Zoom", "Zoom: " + position.zoom);
//
//                if (previousZoomLevel != position.zoom) {
//                    isZooming = true;
//                }
//
//                if (isZooming){
//                    resetDetails();
//                }
//
//                previousZoomLevel = position.zoom;
//            }
//        });
//        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
//            @Override
//            public boolean onClusterClick(Cluster<MyItem> cluster) {
//
//                Log.e(TAG, "onClusterClick: "+cluster. );
//
//                return false;
//            }
//        });


        addItems();


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                resetDetails();
            }
        });

//        mMap.
//        mClusterManager.setAnimation(false);
//        mClusterManager.cluster();

//        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
//            @Override
//            public void onCameraMove() {
//                resetDetails();
////                CameraPosition cameraPosition = mMap.getCameraPosition();
////                Log.e(TAG, "onCameraMove: " + cameraPosition.zoom);
////                if (cameraPosition.zoom > 5.0) {
////                    zoomInData();
//////                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
////                } else {
////                    markerReset();
//////                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
////                }
//            }
//        });


//        markerReset();


//        for (Map.Entry<String, MarkerData> entry : mapLatlog.entrySet()) {
//
//            createMarker(entry.getValue().getLat(), entry.getValue().getLog(), entry.getKey(), "", 1);
//        }

    }

    private void resetDetails() {
        binding.maplayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }


    private void addItems() {

        Random random = new Random();

        for (int i = 0; i < list.size(); i++) {


//            MyItem offsetItem1 = new MyItem(list.get(i).getLatitude(), list.get(i).getLongitude(), list.get(i).getRegionName(), "");
//            mClusterManager.addItem(offsetItem1);
            int s = list.get(i).getSchoolList().size();
            for (int j = 0; j < list.get(i).getSchoolList().size(); j++) {

                SchoolList schoolList = list.get(i).getSchoolList().get(j);


                int n = random.nextInt(s);
                int icPos = 0;
                if (n == j) {
                    icPos = 1;
                }

                BitmapDescriptor[] icon = {BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN), BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED)};


                MyItem offsetItem;
                if (icPos == 1) {
                    offsetItem = new MyItem(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), schoolList.getName(), icon[icPos], true);
                } else {
                    offsetItem = new MyItem(schoolList.getLatitude(), schoolList.getLongitude(), schoolList.getName(), schoolList.getName(), icon[icPos], false);
                }
                mClusterManager.addItem(offsetItem);
            }


        }


    }

    public class ClusterRenderer extends DefaultClusterRenderer<MyItem> {

        public ClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
            clusterManager.setRenderer(this);
        }

        @Override
        protected void onBeforeClusterItemRendered(MyItem markerItem, MarkerOptions markerOptions) {
            if (markerItem.getIcon() != null) {
                markerOptions.icon(markerItem.getIcon()); //Here you retrieve BitmapDescriptor from ClusterItem and set it as marker icon
            }
            markerOptions.visible(true);
        }
    }

    public class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {

        private final IconGenerator mClusterIconGenerator = new IconGenerator(getActivity());

        public MyClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(MyItem item,
                                                   MarkerOptions markerOptions) {

            BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);

            markerOptions.icon(markerDescriptor);
        }

        @Override
        protected int getColor(int clusterSize) {
            String color = "#80DEEA";
            switch (clusterSize) {
                case 10:
                    color = "#4DD0E1";
                    break;
                case 20:
                    color = "#26C6DA";
                    break;
                case 50:
                    color = "#00BCD4";
                    break;
                case 100:
                    color = "#00ACC1";
                    break;
                case 200:
                    color = "#0097A7";
                    break;
                case 500:
                    color = "#00838F";
                    break;
                case 1000:
                    color = "#006064";
                    break;
            }
            return Color.parseColor(color);
        }

        @Override
        protected void onClusterItemRendered(MyItem clusterItem, Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {


//            final Drawable clusterIcon = getResources().getDrawable(R.drawable.ic_lens_black_24dp);
//            clusterIcon.setColorFilter(getResources().getColor(android.R.color.holo_orange_light), PorterDuff.Mode.SRC_ATOP);
//
//            mClusterIconGenerator.setBackground(clusterIcon);
//
//            //modify padding for one or two digit numbers
//            if (cluster.getSize() < 10) {
//                mClusterIconGenerator.setContentPadding(40, 20, 0, 0);
//            }
//            else {
//                mClusterIconGenerator.setContentPadding(30, 20, 0, 0);
//            }

//            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
//            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));

//            int bucket = getBucket(cluster);
//            BitmapDescriptor descriptor = i.get(bucket);
//            if (descriptor == null) {
//                mColoredCircleBackground.getPaint().setColor(getColor(bucket));
//                // you can edit/replace getClusterText to change text of cluster icon
//                descriptor = BitmapDescriptorFactory.fromBitmap(mIconGenerator.makeIcon(getClusterText(bucket)));
//                mIcons.put(bucket, descriptor);
//            }
//            // TODO: consider adding anchor(.5, .5) (Individual markers will overlap more often)
//            markerOptions.icon(descriptor);
        }
    }


    public class CustomClusterRenderer extends DefaultClusterRenderer<MyItem> {

        private final IconGenerator mIconGenerator;
        private ShapeDrawable mColoredCircleBackground;
        private SparseArray<BitmapDescriptor> mIcons = new SparseArray();
        private final float mDensity;
        private Context mContext;

        public CustomClusterRenderer(Context context, GoogleMap map,
                                     ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);


            this.mContext = context;
            this.mDensity = context.getResources().getDisplayMetrics().density;
            this.mIconGenerator = new IconGenerator(context);
            this.mIconGenerator.setContentView(this.makeSquareTextView(context));
//            this.mIconGenerator.setTextAppearance(
//                    com.google.maps.android.R.style.ClusterIcon_TextAppearance);
            this.mIconGenerator.setBackground(this.makeClusterBackground());
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster,
                                               MarkerOptions markerOptions) {
            // Main color
            int clusterColor = mContext.getResources().getColor(R.color.colorPrimary);

            int bucket = this.getBucket(cluster);
            BitmapDescriptor descriptor = this.mIcons.get(bucket);
            if (descriptor == null) {
                this.mColoredCircleBackground.getPaint().setColor(clusterColor);
                descriptor = BitmapDescriptorFactory.fromBitmap(
                        this.mIconGenerator.makeIcon(this.getClusterText(bucket)));
                this.mIcons.put(bucket, descriptor);
            }

            markerOptions.icon(descriptor);
        }

        private SquareTextView makeSquareTextView(Context context) {
            SquareTextView squareTextView = new SquareTextView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            squareTextView.setLayoutParams(layoutParams);
//            squareTextView.setId(com.google.maps.android.R.id.text);
            int twelveDpi = (int) (12.0F * this.mDensity);
            squareTextView.setPadding(twelveDpi, twelveDpi, twelveDpi, twelveDpi);
            return squareTextView;
        }

        private LayerDrawable makeClusterBackground() {
            // Outline color
            int clusterOutlineColor = mContext.getResources().getColor(R.color.white);

            this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
            ShapeDrawable outline = new ShapeDrawable(new OvalShape());
            outline.getPaint().setColor(clusterOutlineColor);
            LayerDrawable background = new LayerDrawable(
                    new Drawable[]{outline, this.mColoredCircleBackground});
            int strokeWidth = (int) (this.mDensity * 3.0F);
            background.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth);
            return background;
        }
    }


}
