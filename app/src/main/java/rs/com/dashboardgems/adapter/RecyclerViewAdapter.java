package rs.com.dashboardgems.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rs.com.dashboardgems.R;
import rs.com.dashboardgems.model.PersonData;

/**
 * Created by yasar on 4/8/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Row> {

    private static final String TAG = "RecyclerViewAdapter";
    private SharedPreferences sharedPreferences;
    public RecyclerViewAdapter(Context context, List<PersonData> list) {
        this.list = list;
        this.context = context;
        this.onCheckBoxClick = (OnCheckBoxClick) context;
        this.sharedPreferences = context.getSharedPreferences("firemustlist", context.MODE_PRIVATE);
    }

    public RecyclerViewAdapter(Fragment context, List<PersonData> list) {
        this.list = list;
        this.context = context.getActivity();
        this.onCheckBoxClick = (OnCheckBoxClick) context;
        this.sharedPreferences = context.getActivity().getSharedPreferences("firemustlist", context.getActivity().MODE_PRIVATE);
    }

    private List<PersonData> list;
    private Context context;
    private OnCheckBoxClick onCheckBoxClick;

    public void updateList(List<PersonData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public Row onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rowall, parent, false);
        return new Row(view);
    }

    @Override
    public void onBindViewHolder(final Row holder, final int position) {
        final PersonData visitorInfo = list.get(position);

        holder.name.setText(visitorInfo.getName());
        String visitorType = visitorInfo.getType().substring(0, 1).toUpperCase()+ visitorInfo.getType().substring(1);;
        holder.visitortype.setText(visitorType);


        if (visitorInfo.getIsSafe()) {
            holder.checkBox.setImageResource(R.drawable.ic_check);

        } else {
            holder.checkBox.setImageResource(R.drawable.ic_uncheck);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                visitorInfo.setServer_is_safe_updated(true);
                if (visitorInfo.getIsSafe()) {
//                    holder.checkBox.setImageResource(R.drawable.ic_uncheck);
                    visitorInfo.setIsSafe(false);

                } else {
//                    holder.checkBox.setImageResource(R.drawable.ic_check);
                    visitorInfo.setIsSafe(true);
                }

                onCheckBoxClick.OnItemClick(position, visitorInfo);
            }
        });
        if (sharedPreferences.getBoolean("who_is", true)) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Row extends RecyclerView.ViewHolder {

        private TextView name, visitortype;
        private ImageView checkBox;
        private CircleImageView profileImage;

        public Row(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            name = (TextView) itemView.findViewById(R.id.name);
            visitortype = (TextView) itemView.findViewById(R.id.visitortype);
            checkBox = (ImageView) itemView.findViewById(R.id.isinorout);
            profileImage = (CircleImageView) itemView.findViewById(R.id.profileimage);
        }
    }

    public interface OnCheckBoxClick {
        void OnItemClick(int pos, PersonData personData);
    }

}