package rs.com.dashboardgems.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.models.Schools;

/**
 * Created by yasar on 10/11/17.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.MyViewHolder> {

    private static final String TAG = "SchoolAdapter";
    private List<Schools> schoolsList;
    private SchoolInterface schoolInterface;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView flag;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            flag = (ImageView) view.findViewById(R.id.flag);

        }
    }

    public SchoolAdapter(Context context, SchoolInterface schoolInterface, List<Schools> schoolsList) {
        this.schoolsList = schoolsList;
        this.context = context;
        this.schoolInterface = schoolInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_school, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Schools schools = schoolsList.get(position);

        if (schools.isFlag()) {
            holder.flag.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.flag.setImageResource(schools.getResourceID());

            holder.itemView.setPadding((int) context.getResources().getDimension(R.dimen._20ssp), (int) context.getResources().getDimension(R.dimen._5ssp), (int) context.getResources().getDimension(R.dimen._20ssp), (int) context.getResources().getDimension(R.dimen._5ssp));
        } else {
            holder.title.setVisibility(View.VISIBLE);
            holder.flag.setVisibility(View.GONE);
            holder.itemView.setPadding((int) context.getResources().getDimension(R.dimen._5ssp), (int) context.getResources().getDimension(R.dimen._5ssp), (int) context.getResources().getDimension(R.dimen._5ssp), (int) context.getResources().getDimension(R.dimen._5ssp));
        }
//        holder.itemView.setPadding((int) context.getResources().getDimension(R.dimen._20ssp), (int) context.getResources().getDimension(R.dimen._10ssp), (int) context.getResources().getDimension(R.dimen._20ssp), (int) context.getResources().getDimension(R.dimen._10ssp));
//        holder.flag.setVisibility(View.GONE);
        if (schools.getShortName() != null) {

            holder.title.setText(schools.getName() + "(" + schools.getShortName() + ")");
        } else {
            holder.title.setText(schools.getName());
        }
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/Geneva.ttf");
        holder.title.setTypeface(type);


        GradientDrawable bgShape = (GradientDrawable) holder.itemView.getBackground();
        if (schools.isTrue()) {
//            holder.itemView.setBackgroundColor(Color.LTGRAY);


            bgShape.setColor(Color.parseColor("#595959"));
//            holder.itemView.setBackgroundColor(Color.parseColor("#81C786"));
            holder.title.setTextColor(Color.WHITE);
        } else {
            holder.title.setTextColor(Color.BLACK);
//            bgShape.setColor(Color.parseColor("#D8F0D9"));
            bgShape.setColor(Color.parseColor("#cfd2cd"));
//            holder.itemView.setBackgroundColor(Color.parseColor("#D8F0D9"));
//            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e(TAG, "onClick: " + getCount());
                if (getCount() + 1 > 2) {

                    if (schools.isTrue() && getCount() > 2) {
                        schools.setTrue(false);
                        schoolInterface.position(position, schools, -1);
                    } else if (!schools.isTrue()) {
                        schools.setTrue(true);
                        schoolInterface.position(position, schools, 1);
                    } else {
                        Toast.makeText(context, "Please select atleast two schools to compare", Toast.LENGTH_SHORT).show();
                    }
                    notifyItemChanged(position);
                }


            }
        });

    }

    private int getCount() {
        int count = 0;
        for (int i = 0; i < schoolsList.size(); i++) {
            if (schoolsList.get(i).isTrue()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return schoolsList.size();
    }

    public interface SchoolInterface {
        void position(int pos, Schools schools, int count);

    }
}