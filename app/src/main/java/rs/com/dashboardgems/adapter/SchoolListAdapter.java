package rs.com.dashboardgems.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.model.SchoolList;


/**
 * Created by yasar on 4/8/17.
 */

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.Row> {

    private static final String TAG = "RecyclerViewAdapter";

    public SchoolListAdapter(Context context, List<SchoolList> list) {
        this.list = list;
        this.context = context;
        this.onCheckBoxClick = (OnCheckBoxClick) context;
    }

    public SchoolListAdapter(Fragment context, List<SchoolList> list) {
        this.list = list;
        this.context = context.getActivity();
        this.onCheckBoxClick = (OnCheckBoxClick) context;
    }

    private List<SchoolList> list;
    private Context context;
    private OnCheckBoxClick onCheckBoxClick;

    public void updateList(List<SchoolList> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public Row onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.schoollistrow, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(final Row holder, final int position) {
        final SchoolList visitorInfo = list.get(position);

        holder.schoolname.setText(visitorInfo.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckBoxClick.OnItemClick(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Row extends RecyclerView.ViewHolder {

        private TextView schoolname;

        public Row(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            schoolname = (TextView) itemView.findViewById(R.id.schoolname);

        }
    }

    public interface OnCheckBoxClick {
        void OnItemClick(int pos);
    }

}