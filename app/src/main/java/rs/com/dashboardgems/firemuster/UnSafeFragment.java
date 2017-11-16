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

public class UnSafeFragment extends BaseFragment implements RecyclerViewAdapter.OnCheckBoxClick {
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

    public static UnSafeFragment getInstance(List<PersonData> personDataList) {
        UnSafeFragment fragment = new UnSafeFragment();
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

                if (!personData.getIsSafe()) {
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
