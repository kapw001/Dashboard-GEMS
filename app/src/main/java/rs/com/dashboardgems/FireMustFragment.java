package rs.com.dashboardgems;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import rs.com.dashboardgems.adapter.GridViewRecyclerAdapter;
import rs.com.dashboardgems.firemuster.AllFragment;
import rs.com.dashboardgems.firemuster.PassListInterface;
import rs.com.dashboardgems.firemuster.SafeFragment;
import rs.com.dashboardgems.firemuster.StaffFragment;
import rs.com.dashboardgems.firemuster.StudentFragment;
import rs.com.dashboardgems.firemuster.UnSafeFragment;
import rs.com.dashboardgems.firemuster.VisitorFragment;
import rs.com.dashboardgems.model.PersonData;
import rs.com.dashboardgems.model.ResultResponse;
import rs.com.dashboardgems.model.TabMenu;
import rs.com.dashboardgems.models.SchoolDetailsResult;
import rs.com.dashboardgems.models.SchoolList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yasar on 15/11/17.
 */

public class FireMustFragment extends BaseFragment implements GridViewRecyclerAdapter.OnItemClickListener, PassListInterface {


    private LinearLayout alllayout, visitorlayout, stafflayout, studentlayout;
    private TextView all, student, visitor, staff;
    private TextView allcount, studentcount, visitorcount, staffcount;
    private TextView reset, save, incedentname, timing, schoolname;
    private ImageView refresh;
    private EditText search;

    private View vilast;
    private static final String TAG = "FireMusterActivity";
    private List<PersonData> personDataList;
    private List<PersonData> defaultList;


    private rs.com.dashboardgems.firemuster.BaseFragment fragment = null;

    private LinearLayout ascending, descending, selectall;
    private ImageView ascendingimg, descendingimg, selectallimg;
    private boolean isDesending = true;
    private boolean isAscending = true;
    private boolean isSelectAll = true;
    private SharedPreferences sharedPreferences;

    private ResultResponse resultResponse;

    private RecyclerView tabrecyclerView;
    private GridViewRecyclerAdapter gridViewRecyclerAdapter;

    private List<TabMenu> listgrid;

    private TextView closeinstance;

    private List<PersonData> serverUpdateList;

    private int id;
    private String school_name;

    private boolean isActivityFirstTimeRunning = true;


    private RelativeLayout offlinedatalayout;

    private TextView instancenamedate;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RelativeLayout relativeLayout;
    private ImageView refreshwhois;

    private Spinner schoolspinner, regionspinner;

    private List<SchoolDetailsResult> AllschoolResultList = new ArrayList<>();
    private List<PersonData> AllPersonDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fire_muster, container, false);


        AllschoolResultList.addAll(getSchoolList(getActivity()));
        AllPersonDataList.addAll(getSchoolPersonList(getActivity()));

        offlinedatalayout = (RelativeLayout) view.findViewById(R.id.offlinedatalayout);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        schoolspinner = (Spinner) view.findViewById(R.id.schoolspinner);
        regionspinner = (Spinner) view.findViewById(R.id.regionspinner);


        schoolspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                loadFireMustList(0);

                String item = parent.getItemAtPosition(position).toString();
                ((TextView) view).setTextColor(Color.BLACK);
                schoolname.setText(item);

                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<String> categories = new ArrayList<String>();

        for (int i = 0; i < AllschoolResultList.size(); i++) {
            SchoolDetailsResult schoolDetailsResult = AllschoolResultList.get(i);
            categories.add(schoolDetailsResult.getRegionName());
        }

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        regionspinner.setAdapter(dataAdapter);
        final List<String> categories1 = new ArrayList<String>();
        final ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories1);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                List<SchoolList> schoolLists = AllschoolResultList.get(position).getSchoolList();

                categories1.clear();

                for (int i = 0; i < schoolLists.size(); i++) {
                    SchoolList sc = schoolLists.get(i);
                    categories1.add(sc.getName());
                }
                // attaching data adapter to spinner
                schoolspinner.setAdapter(dataAdapter1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        swipeRefreshLayout.setEnabled(false);


        relativeLayout = (RelativeLayout) view.findViewById(R.id.lastupdatelayout);
        refreshwhois = (ImageView) view.findViewById(R.id.refreshwhois);

        relativeLayout.setVisibility(View.GONE);
        refreshwhois.setVisibility(View.VISIBLE);

        serverUpdateList = new ArrayList<>();
        timing = (TextView) view.findViewById(R.id.timing);
        schoolname = (TextView) view.findViewById(R.id.schoolname);
        instancenamedate = (TextView) view.findViewById(R.id.instancenamedate);

        instancenamedate.setVisibility(View.GONE);
        schoolname.setTextColor(Color.parseColor("#00216f"));

        closeinstance = (TextView) view.findViewById(R.id.closeinstance);

        sharedPreferences = getActivity().getSharedPreferences("firemustlist", MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("who_is", true);
        editor.commit();


        personDataList = new ArrayList<>();
        defaultList = new ArrayList<>();


        listgrid = new ArrayList<>();

        listgrid.add(new TabMenu("All", "0"));
//        listgrid.add(new TabMenu("Safe", "0"));
//        listgrid.add(new TabMenu("Un Safe", "0"));
        listgrid.add(new TabMenu("Visitor", "0"));
        listgrid.add(new TabMenu("Staff", "0"));
        listgrid.add(new TabMenu("Student", "0"));


        gridViewRecyclerAdapter = new GridViewRecyclerAdapter(getActivity(), this, listgrid);

        tabrecyclerView = (RecyclerView) view.findViewById(R.id.tabrecyclerview);
// set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        gridRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        tabrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)); // set LayoutManager to RecyclerView
        tabrecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
//        tabrecyclerView.addItemDecoration(new EqualSpaceItemDecoration(5));
//        tabrecyclerView.addItemDecoration(new SimpleItemDecorator(5));
        tabrecyclerView.setHasFixedSize(true);
        tabrecyclerView.setAdapter(gridViewRecyclerAdapter);

//        id = getIntent().getIntExtra("school_id", 0);
//        school_name = getIntent().getStringExtra("school_name");
//
//        schoolname.setText("GEMS SCHOOLS");

//        if (Utils.isOnline(getApplicationContext())) {
        loadFireMustList(0);

        addFragment("all");
//        navigationView.setItemIconTintList(null);
//        navigationView.getMenu().findItem(R.id.firemusterList).setChecked(true);

        refresh = (ImageView) view.findViewById(R.id.refresh);
        closeinstance = (TextView) view.findViewById(R.id.closeinstance);
        refresh.setVisibility(View.GONE);
        refreshwhois.setVisibility(View.GONE);


        return view;

    }


    public static List<SchoolDetailsResult> getSchoolList(Context context) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<SchoolDetailsResult>>() {
        }.getType();
        return gson.fromJson(rs.com.dashboardgems.Utils.loadJSONFromAsset(context), listType);
    }

    public static List<PersonData> getSchoolPersonList(Context context) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PersonData>>() {
        }.getType();
        return gson.fromJson(rs.com.dashboardgems.Utils.loadJSONFromAssetRecords(context), listType);
    }


    @Override
    public void position(TabMenu itemName) {

        addFragment(itemName.getTabName().toString());

    }


    private void addFragment(String name) {
        switch (name.toLowerCase()) {
            case "all":
                fragment = AllFragment.getInstance(personDataList);

                break;
            case "safe":
                fragment = SafeFragment.getInstance(personDataList);
                break;
            case "un safe":
                fragment = UnSafeFragment.getInstance(personDataList);
                break;

            case "visitor":
                fragment = VisitorFragment.getInstance(personDataList);
                break;

            case "staff":
                fragment = StaffFragment.getInstance(personDataList);

                break;

            case "student":
                fragment = StudentFragment.getInstance(personDataList);
                break;
        }

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

//        fragment.setPassListInterface(this);

    }


    private void loadFireMustList(final int id) {

        List<PersonData> list2 = AllPersonDataList;
        List<PersonData> list = new ArrayList<>();

        Random random = new Random();

        int total = random.nextInt(list2.size());

        for (int i = 0; i < total; i++) {

            int n = random.nextInt(list2.size());

            list.add(list2.get(n));

        }


//        String[] name = {"Visitor", "Student", "Staff"};
//        String[] cl = {"Class I", "Class II"};
//
//        Random random = new Random();
//
//        for (int i = 0; i < 100; i++) {
//
//            int n = random.nextInt(name.length);
//            int c = random.nextInt(2);
//
//            PersonData personData = new PersonData();
//            personData.setName("Karthik");
//            personData.setId(i);
//            personData.setServer_is_safe_updated(false);
//            personData.setIsSafe(false);
//            personData.setPhoto(null);
//            personData.setSchoolId(i);
//            personData.setType(name[n]);
//            personData.setStudClass(cl[c]);
//
//            list.add(personData);
//        }

        Collections.sort(list, new Comparator<PersonData>() {
            @Override
            public int compare(PersonData lhs, PersonData rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSchoolId(id);
        }

        personDataList.clear();
        personDataList.addAll(list);
        defaultList.clear();
        defaultList.addAll(list);


        if (fragment != null) {
            fragment.loadData(list);
        }

        showTotalCount(list);

        timing.setText(rs.com.dashboardgems.firemuster.Utils.getTimeAMPM());

    }


    private void showTotalCount(final List<PersonData> list) {

        new AsyncTask<List<TabMenu>, List<TabMenu>, List<TabMenu>>() {
            @Override
            protected List<TabMenu> doInBackground(List<TabMenu>... lists) {
                List<PersonData> visitorCountList = new ArrayList<>();
                List<PersonData> staffCountList = new ArrayList<>();
                List<PersonData> studentCountList = new ArrayList<>();
                List<PersonData> safeCountList = new ArrayList<>();
                List<PersonData> unsafeCountList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    PersonData personData = list.get(i);

                    if (personData.getType().toLowerCase().toString().equalsIgnoreCase("Staff".toLowerCase())) {
                        staffCountList.add(personData);
                    }

                    if (personData.getType().toLowerCase().toString().equalsIgnoreCase("Student".toLowerCase().toString())) {
                        studentCountList.add(personData);
                    }

                    if (personData.getType().toLowerCase().toString().equalsIgnoreCase("Visitor".toLowerCase())) {
                        visitorCountList.add(personData);
                    }

                    if (personData.getIsSafe() == true) {
                        safeCountList.add(personData);
                    }

                    if (personData.getIsSafe() != true) {
                        unsafeCountList.add(personData);
                    }
                }

                int acount = list.size();
                int vcount = visitorCountList.size();
                int stffcount = staffCountList.size();
                int scount = studentCountList.size();
                int safecount = safeCountList.size();
                int unsafecount = unsafeCountList.size();

                List<TabMenu> List = new ArrayList<>();

                List.add(new TabMenu("All", "" + acount));
//                List.add(new TabMenu("Safe", "" + safecount));
//                List.add(new TabMenu("Un Safe", "" + unsafecount));
                List.add(new TabMenu("Visitor", "" + vcount));
                List.add(new TabMenu("Staff", "" + stffcount));
                List.add(new TabMenu("Student", "" + scount));


                return List;
            }

            @Override
            protected void onPostExecute(List<TabMenu> tabMenus) {
//                super.onPostExecute(tabMenus);
                gridViewRecyclerAdapter.update(tabMenus);
            }
        }.execute();


    }

    public void resetList() {
        for (int i = 0; i < personDataList.size(); i++) {
            personDataList.get(i).setIsSafe(false);
        }

        if (fragment != null) {
            fragment.loadData(personDataList);
        }

        checkSelectState();
    }

    public void ascending() {

        Collections.sort(personDataList, new Comparator<PersonData>() {
            @Override
            public int compare(PersonData lhs, PersonData rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        if (fragment != null) {
            fragment.loadData(personDataList);
        }
    }


    public void descending() {
        Collections.sort(personDataList, new Comparator<PersonData>() {
            @Override
            public int compare(PersonData lhs, PersonData rhs) {
                return rhs.getName().compareTo(lhs.getName());
            }
        });

        if (fragment != null) {
            fragment.loadData(personDataList);
        }
    }

    public void defaultList() {
        if (fragment != null && defaultList.size() > 0) {
            fragment.loadData(defaultList);
        } else {
            Log.e(TAG, "defaultList: no data ");
        }
    }

    public void searchlist(String search) {


        List<PersonData> searchList = new ArrayList<>();

        for (int i = 0; i < personDataList.size(); i++) {
            PersonData personData = personDataList.get(i);

            if (personData.getName().toLowerCase().contains(search.toLowerCase())) {
                searchList.add(personData);
                Log.e(TAG, "searchlist: " + personData.getName());
            }

        }
        if (fragment != null) {
            fragment.loadData(searchList);
        }

    }

    private void selectUnselectAll(boolean isSelect) {

        List<PersonData> list = new ArrayList<>(personDataList.size());
        list.clear();
        list.addAll(personDataList);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsSafe(isSelect);
        }
        if (fragment != null) {
            fragment.loadData(list);
        }

    }

    public void checkSelectState() {


    }

    @Override
    public void updateList() {

    }

    @Override
    public void addUpdateList(PersonData personData) {

    }

}
