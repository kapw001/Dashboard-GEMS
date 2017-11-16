package rs.com.dashboardgems.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.model.TabMenu;

/**
 * Created by yasar on 2/8/17.
 */

public class GridViewRecyclerAdapter extends RecyclerView.Adapter<GridViewRecyclerAdapter.MyViewHolder> {

    public int selectedPosition = 0;
    private OnItemClickListener onItemClickListener;

    public GridViewRecyclerAdapter(Context context, List<TabMenu> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = (OnItemClickListener) context;
    }

    public GridViewRecyclerAdapter(Context context,OnItemClickListener onItemClickListener, List<TabMenu> list) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }
    private Context context;
    private List<TabMenu> list;

    public void update(List<TabMenu> list) {
//        this.list = new ArrayList<>();
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabmenu, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final TabMenu tabMenu = list.get(position);

        holder.name.setText(tabMenu.getTabName());
        holder.count.setText(tabMenu.getTotalCount());

//        if (Integer.parseInt(holder.count.getText().toString()) > 0) {
//            holder.count.setVisibility(View.VISIBLE);
//        } else {
//            holder.count.setVisibility(View.GONE);
//        }

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.count.setBackground(ContextCompat.getDrawable(context, R.drawable.badge_backgroundwhite));
            holder.name.setTextColor(Color.parseColor("#ffffff"));
            holder.count.setTextColor(Color.parseColor("#000000"));

        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.count.setBackground(ContextCompat.getDrawable(context, R.drawable.badge_background));
            holder.name.setTextColor(Color.parseColor("#000000"));
            holder.count.setTextColor(Color.parseColor("#ffffff"));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.position(tabMenu);
                selectedPosition = position;
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, count;// init the item view's
//        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
// get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);
//            linearLayout = (LinearLayout) itemView.findViewById(R.id.tabmenu);

        }
    }

    public interface OnItemClickListener {
        void position(TabMenu itemName);
    }
}