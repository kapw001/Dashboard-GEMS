package rs.com.dashboardgems.firemuster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.adapter.RecyclerViewAdapter;
import rs.com.dashboardgems.model.PersonData;


/**
 * Created by yasar on 18/8/17.
 */

public class StaffFragment extends BaseFragment implements RecyclerViewAdapter.OnCheckBoxClick {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<PersonData> visitorInfoList;
    private EditText search;

    private LinearLayout ascending, descending, selectall;
    private ImageView ascendingimg, descendingimg, selectallimg;
    private boolean isDesending = true;
    private boolean isAscending = true;
    private boolean isSelectAll = true;

    private List<PersonData> staffList;
    private TextView msg;

    public static StaffFragment getInstance(List<PersonData> personDataList) {
        StaffFragment fragment = new StaffFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("personDataList", (Serializable) personDataList);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fraglayout, container, false);

        msg = (TextView) view.findViewById(R.id.msg);

        visitorInfoList = new ArrayList<>();
        staffList = new ArrayList<>();


//        selectallimg = (ImageView) view.findViewById(R.id.selectallimg);
//        ascendingimg = (ImageView) view.findViewById(R.id.ascendingimg);
//        descendingimg = (ImageView) view.findViewById(R.id.descendingimg);

//        descending = (LinearLayout) view.findViewById(R.id.descending);
//        ascending = (LinearLayout) view.findViewById(R.id.ascending);
//        selectall = (LinearLayout) view.findViewById(R.id.selectall);
//
//
//        descending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (isDesending) {
//                    descendingimg.setImageResource(R.drawable.ic_descending_disabled);
//                    isDesending = false;
//                } else {
//                    descendingimg.setImageResource(R.drawable.ic_descending_enabled);
//                    isDesending = true;
//                }
//
//            }
//        });
//
//        ascending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (isAscending) {
//                    ascendingimg.setImageResource(R.drawable.ic_ascending_disabled);
//                    isAscending = false;
//                } else {
//                    ascendingimg.setImageResource(R.drawable.ic_ascending_enabled);
//                    isAscending = true;
//                }
//
//            }
//        });
//
//        selectall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (isSelectAll) {
//                    selectallimg.setImageResource(R.drawable.ic_check);
//                    isSelectAll = false;
//                } else {
//                    selectallimg.setImageResource(R.drawable.ic_uncheck);
//                    isSelectAll = true;
//                }
//
//                selectUnselectAll(!isSelectAll);
//            }
//        });
//
//        search = (EditText) view.findViewById(R.id.search);
//
//        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
//                    return true;
//                }
//                return false;
//            }
//        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(this, visitorInfoList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                linearLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        visitorInfoList = (List<PersonData>) getArguments().getSerializable("personDataList");

        loadData(visitorInfoList);

        return view;
    }


    public void loadData(List<PersonData> list) {


        if (list.size() > 0) {

            staffList.clear();

            for (int i = 0; i < list.size(); i++) {
                PersonData personData = list.get(i);

                if (personData.getType().toLowerCase().toString().equalsIgnoreCase("Staff".toLowerCase())) {
                    staffList.add(personData);
                }
            }


            recyclerViewAdapter.updateList(staffList);
            msg.setVisibility(View.GONE);
        } else {
            msg.setVisibility(View.VISIBLE);
        }

    }


    private void selectUnselectAll(boolean isSelect) {


        for (int i = 0; i < staffList.size(); i++) {

            staffList.get(i).setIsSafe(isSelect);

        }

        recyclerViewAdapter.updateList(staffList);

    }

    @Override
    public void OnItemClick(int pos,PersonData personData) {

//        passListInterface.addUpdateList(personData);
    }
}
