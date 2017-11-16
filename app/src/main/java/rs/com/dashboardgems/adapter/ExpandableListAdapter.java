package rs.com.dashboardgems.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.model.PersonData;


/**
 * Created by yasar on 9/8/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ExpandableListAdapter";
    private SharedPreferences sharedPreferences;
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<PersonData>> _listDataChild;
    private OnCheckBoxClick onCheckBoxClick;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<PersonData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.onCheckBoxClick = (OnCheckBoxClick) context;
        this.sharedPreferences = context.getSharedPreferences("firemustlist", context.MODE_PRIVATE);
    }

    public ExpandableListAdapter(Fragment context, List<String> listDataHeader,
                                 HashMap<String, List<PersonData>> listChildData) {
        this._context = context.getActivity();
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.onCheckBoxClick = (OnCheckBoxClick) context;
        this.sharedPreferences = context.getActivity().getSharedPreferences("firemustlist", context.getActivity().MODE_PRIVATE);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    private static class ViewHolder {

        protected ImageView checkBox;
        private TextView name, visitorType;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final PersonData visitorInfo = (PersonData) getChild(groupPosition, childPosition);


        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row1, null);
            holder.name = (TextView) convertView
                    .findViewById(R.id.name);
            holder.visitorType = (TextView) convertView
                    .findViewById(R.id.visitortype);
            holder.checkBox = (ImageView) convertView.findViewById(R.id.isinorout);

            convertView.setTag(holder);

            convertView.setTag(R.id.name, holder.name);
            convertView.setTag(R.id.visitortype, holder.visitorType);
            convertView.setTag(R.id.isinorout, holder.checkBox);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setTag(childPosition); // This line is important.

        if (visitorInfo.getIsSafe()) {
            holder.checkBox.setImageResource(R.drawable.ic_check);

        } else {
            holder.checkBox.setImageResource(R.drawable.ic_uncheck);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                visitorInfo.setServer_is_safe_updated(true);

                int getPosition = (Integer) holder.checkBox.getTag();
                if (visitorInfo.getIsSafe()) {
//                    holder.checkBox.setImageResource(R.drawable.ic_uncheck);
                    _listDataChild.get(_listDataHeader.get(groupPosition)).get(getPosition).setIsSafe(false);

                } else {
//                    holder.checkBox.setImageResource(R.drawable.ic_check);
                    _listDataChild.get(_listDataHeader.get(groupPosition)).get(getPosition).setIsSafe(true);
                }

                onCheckBoxClick.OnItemClick(getPosition, visitorInfo);
            }
        });
        holder.name.setText(visitorInfo.getName());
        String visitorType = visitorInfo.getType().substring(0, 1).toUpperCase() + visitorInfo.getType().substring(1);
        holder.visitorType.setText(visitorType);
        if (sharedPreferences.getBoolean("who_is", true)) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {


        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded,
                             View convertView, ViewGroup parent) {

        try {


            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_group, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);

//        headerTitle = headerTitle.substring(0, 1).toUpperCase() + headerTitle.substring(1);
//
//        headerTitle = headerTitle.replace("-", " ");

//            if (headerTitle.length() > 0) {
//
//                String[] parts = headerTitle.split("-");
//                String part1 = parts[0]; // 004
//                String part2 = parts[1]; // 034556
//
//                part1 = part1.substring(0, 1).toUpperCase() + part1.substring(1);
//                part2 = part2.toUpperCase();
//
//                headerTitle = part1 + " " + part2;
//            }

            Log.e(TAG, "getGroupView:  text " + headerTitle);

            lblListHeader.setText(headerTitle);
            lblListHeader.setTextColor(Color.BLACK);
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.indicator);
            imageView.setTag(groupPosition);
////        if (_listDataChild.get(_listDataHeader.get(groupPosition)).size() > 0) {
////            imageView.setImageResource(R.mipmap.ic_plus);
////        }
//
//
            if (isExpanded) {
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                notifyDataSetChanged();
//
//            }
//        });
        } catch (ArrayIndexOutOfBoundsException e) {

            e.printStackTrace();
        }

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnCheckBoxClick {
        void OnItemClick(int pos, PersonData personData);
    }
}